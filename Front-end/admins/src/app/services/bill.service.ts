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



  getBillDateNow(firstName: string): Observable<[Bill]> {
    return this.apiService.get<[Bill]>(`${this.apiService.urls.bill}/billNow/cus/${firstName}`);
  }

  getBillDate(startDate: Date, endDate: Date, firstName: string): Observable<[Bill]> {
    return this.apiService.get<[Bill]>(`${this.apiService.urls.bill}/billDate/${startDate}/${endDate}/cus/${firstName}`);
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

   update(bill: Bill) {
    const formData = new FormData();
    formData.append('id' , bill.id.toString());
    formData.append('deliveryAddress' , bill.deliveryAddress);
      return this.apiService.put<Bill>(this.apiService.urls.bill , formData);
  }
}
