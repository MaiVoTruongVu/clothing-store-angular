import { Bill } from './../models/bill';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Bill]> {
    return this.apiService.get<[Bill]>(this.apiService.urls.bill);
  }
  // tslint:disable-next-line: typedef
  add(id: number, bill: Bill) {
    const formData = new FormData();
    formData.append('deliveryAddress' , bill.deliveryAddress);
    formData.append('customer' , id.toString());
    return this.apiService.post<Bill>(this.apiService.urls.bill , formData);
  }

  getId(id: number): Observable<Bill> {
    return this.apiService.get<Bill>(`${this.apiService.urls.bill}/${id}`);
  }

  delete(id: number): Observable<Bill> {
    return this.apiService.delete<Bill>(`${this.apiService.urls.bill}/${id}`);
   }

   getCustomerById(id: number): Observable<[Bill]> {
   return this.apiService.get<[Bill]>(`${this.apiService.urls.bill}/findByCusId/${id}`);
   }
}
