import { Customer, PageRequest } from 'build/openapi/models';
import { DataSource, CollectionViewer } from '@angular/cdk/collections';
import { BehaviorSubject, Observable } from 'rxjs';
import { CustomerService } from 'build/openapi/services';

export class CustomerDataSource extends DataSource<Customer> {
  private customerSubject = new BehaviorSubject<Customer[]>([]);

  constructor(private customerService: CustomerService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<Customer[]> {
    return this.customerSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.customerSubject.complete();
  }

  loadCustomers(
    page = 0,
    size = 10,
    sortDirection = 'UNSORTED' as const,
  ) {
    let pageRequest: PageRequest = {
      page: page,
      size: size,
      sort:  sortDirection,
    };
    this.customerService
      .getAllCustomers({ page: pageRequest })
      .pipe()
      .subscribe((customers) => {
        return this.customerSubject.next(
          customers.overviewItems === undefined ? [] : customers.overviewItems
        );
      });
  }
}
