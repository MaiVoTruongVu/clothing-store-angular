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
   sortName(): Observable<[Customer]> {
     return this.apiService.get<[Customer]>(`${this.apiService.urls.customer}/sort`);
   }


   // tslint:disable-next-line: typedef
   add(cus) {
    const formData = new FormData();
    formData.append('lastName' , cus.lastName);
    formData.append('firstName' , cus.firstName);
    formData.append('email' , cus.email);
    formData.append('phone' , cus.phone);
    formData.append('address' , cus.address);
    return this.apiService.post<Customer>(this.apiService.urls.customerPost , formData);
  }
}
