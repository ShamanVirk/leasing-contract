import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CustomerService } from 'build/openapi/services';
import { tap } from 'rxjs';
import { CustomerDataSource } from './customer.datasource';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit, AfterViewInit {

  displayedColumns = ['id', 'last-name', 'first-name', 'birthdate'];
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
    this.paginator.page
      .pipe(
        tap(() => this.dataSource.loadCustomers(
          this.paginator.pageIndex,
          this.paginator.pageSize
        ))
      )
      .subscribe();
  }
}
