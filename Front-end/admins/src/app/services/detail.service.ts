import { Detail } from './../models/detail';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DetailService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Detail]> {
    return this.apiService.get<[Detail]>(this.apiService.urls.detail);
  }

  getId(id: number): Observable<Detail> {
    return this.apiService.get<Detail>(`${this.apiService.urls.detail}/${id}`);
  }

  delete(id: number): Observable<Detail> {
    return this.apiService.delete<Detail>(`${this.apiService.urls.detail}/${id}`);
  }

  getBillById(id: number): Observable<[Detail]> {
    return this.apiService.get<[Detail]>(`${this.apiService.urls.detail}/findBillId/${id}`);
  }

  statisticDetail(startDate: Date, endDate: Date, firstName: string): Observable<[Detail]> {
    return this.apiService.get<[Detail]>(`${this.apiService.urls.detail}/statistic/${startDate}/${endDate}/cus/${firstName}`);
  }
  dateNow(firstName: string): Observable<[Detail]> {
    return this.apiService.get<[Detail]>(`${this.apiService.urls.detail}/dateNow/cus/${firstName}`);
  }

}
