import { Producer } from './../models/producer';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProducerService {


  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Producer]> {
    return this.apiService.get<[Producer]>(this.apiService.urls.producer);
  }

  getId(id: number): Observable<Producer> {
    return this.apiService.get<Producer>(`${this.apiService.urls.producer}/${id}`);
  }

  add(pro: Producer) {
    const formData = new FormData();
    formData.append('name' , pro.name);
    formData.append('address' , pro.address);
    formData.append('phone' , pro.phone);
      return this.apiService.post<Producer>(this.apiService.urls.producer , formData);
  }
  update(pro: Producer) {
    const formData = new FormData();
    formData.append('id' , pro.id.toString());
    formData.append('name' , pro.name);
    formData.append('address' , pro.address);
    formData.append('phone' , pro.phone);
      return this.apiService.put<Producer>(this.apiService.urls.producer , formData);
  }

  delete(id: number): Observable<Producer> {
    return this.apiService.delete<Producer>(`${this.apiService.urls.producer}/${id}`);
   }
}
