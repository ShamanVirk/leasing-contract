import { Customer, CustomerPageResponse, PageRequest } from 'build/openapi/models';
import { DataSource, CollectionViewer } from '@angular/cdk/collections';
import { BehaviorSubject, Observable, catchError, finalize, of } from 'rxjs';
import { CustomerService } from 'build/openapi/services';

export class CustomerDataSource extends DataSource<Customer> {
  private customerSubject = new BehaviorSubject<Customer[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  public customersData: Customer[] = [];

  constructor(private customerService: CustomerService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<Customer[]> {
    return this.customerSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.customerSubject.complete();
    this.loadingSubject.complete();
  }

  loadCustomers(
    page = 0,
    size = 10,
    sortDirection = 'UNSORTED' as const,
  ) {
    this.loadingSubject.next(true);
    let pageRequest: PageRequest = {
      page: page,
      size: size,
      sort:  sortDirection,
    };
    this.customerService
      .getAllCustomers({ page: pageRequest })
      .pipe(
        catchError(() => of(this.getEmptyCustomerPageResponse())),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe((customers) => {
        this.customersData = customers.overviewItems ? customers.overviewItems : [] ;
        return this.customerSubject.next(
          this.customersData
        );
      });
  }

  getEmptyCustomerPageResponse() {
    let t: CustomerPageResponse = {
      numberOfItems: 0,
      numberOfPages: 0,
      page: 0,
      size: 0,
      sort: 'UNSORTED',
      overviewItems: undefined
    };
    return t;
  }
}
