import { Detail } from './../models/detail';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DetailService {

  constructor(private  apiService: ApiService) { }

  getAll(): Observable<[Detail]> {
    return this.apiService.get<[Detail]>(this.apiService.urls.detail);
  }

  getId(id: number): Observable<Detail> {
    return this.apiService.get<Detail>(`${this.apiService.urls.detail}/${id}`);
  }

  add(idBill: number, idPro: number , quantity: number, price: number) {
    const formData = new FormData();
    formData.append('quantity' , quantity.toString());
    formData.append('price' , price.toString());
    formData.append('product' , idPro.toString());
    formData.append('bill' , idBill.toString());
    return this.apiService.post<Detail>(this.apiService.urls.detail , formData);
  }

  delete(id: number): Observable<Detail> {
    return this.apiService.delete<Detail>(`${this.apiService.urls.detail}/${id}`);
   }

   getBillById(id: number): Observable<[Detail]> {
   return this.apiService.get<[Detail]>(`${this.apiService.urls.detail}/findBillId/${id}`);
   }
}
