import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  baseUrl = 'http://localhost:8080/front/';
  urls = {
    banner: this.baseUrl + 'banners',
    producer: this.baseUrl + 'producers',
    size: this.baseUrl + 'sizes',
    color: this.baseUrl + 'colors',
    type: this.baseUrl + 'types',
    customer: this.baseUrl + 'customers',
    customerPost: this.baseUrl + 'customersPost',
    product: this.baseUrl + 'products',
    bill: this.baseUrl + 'bills',
    detail: this.baseUrl + 'details',
    image: this.baseUrl + 'img',
  };

  constructor(private http: HttpClient) { }

  get<T>(url: string) {
    return this.http.get<T>(url);
  }

  post<T>(url: string , formData: FormData) {
    return this.http.post<T>(url, formData);
  }

  put<T>(url: string , formData: FormData) {
    return this.http.put<T>(url , formData);
  }
  delete<T>(url: string) {
    return this.http.delete<T>(url);
  }

  addFormData(url: string , formData: FormData) {
    return this.http.post<any>(url, formData, {
      reportProgress: true
    });
  }

  upadateFormData(url: string , formData: FormData) {
    return this.http.put<any>(url, formData, {
      reportProgress: true
    });
  }
}
