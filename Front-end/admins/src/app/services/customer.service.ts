import { Customer } from './../models/customer';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Customer]> {
    return this.apiService.get<[Customer]>(this.apiService.urls.customer);
  }
  getId(id: number): Observable<Customer> {
    return this.apiService.get<Customer>(`${this.apiService.urls.customer}/${id}`);
  }
  delete(id: number): Observable<Customer> {
    return this.apiService.delete<Customer>(`${this.apiService.urls.customer}/${id}`);
   }

   searchName(firstName: string): Observable<[Customer]> {
     return this.apiService.get<[Customer]>(`${this.apiService.urls.customer}/searchName/${firstName}`);
   }
  //  sortName(): Observable<[Customer]> {
  //    return this.apiService.get<[Customer]>(`${this.apiService.urls.customer}/sort`);
  //  }
}
