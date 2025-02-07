import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Customer, PageRequest } from 'build/openapi/models';
import { CustomerService } from 'build/openapi/services';
import { merge, tap } from 'rxjs';
import { CustomerDataSource } from './customer.datasource';
import { AddDialogComponent } from './dialog/add/add.dialog.component';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit, AfterViewInit {

  displayedColumns = ['id', 'last-name', 'first-name', 'birthdate', 'actions'];
  dataSource: CustomerDataSource;

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator = {} as MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort = {} as MatSort;
  @ViewChild('filter', { static: true }) filter: ElementRef = {} as ElementRef;

  constructor(
    public customerService: CustomerService,
    public dialog: MatDialog
  ) {
    this.dataSource = new CustomerDataSource(this.customerService);
  }

  ngOnInit() {
    this.dataSource.loadCustomers(0);
  }

  ngAfterViewInit(): void {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => {
          let direction = this.sort.direction === '' ? 'UNSORTED' : this.sort.direction.toUpperCase();
          return this.dataSource.loadCustomers(
            this.paginator.pageIndex,
            this.paginator.pageSize,
            direction as PageRequest["sort"]
          )
        })
      )
      .subscribe();
  }

  addNew() {
    let customer : Customer = {firstName:'', lastName: '', birthDate: ''};
    let dialogRef = this.dialog.open(AddDialogComponent, {
      data: {body: customer}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 200) {
        this.paginator._changePageSize(this.paginator.pageSize);
      }
    });
  }
}
