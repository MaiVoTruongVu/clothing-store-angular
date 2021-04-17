import { Size } from './../models/size';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SizeService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Size]> {
    return this.apiService.get<[Size]>(this.apiService.urls.size);
  }

  getId(id: number): Observable<Size> {
    return this.apiService.get<Size>(`${this.apiService.urls.size}/${id}`);
  }

  add(size: Size) {
    const formData = new FormData();
    formData.append('name' , size.name);
      return this.apiService.post<Size>(this.apiService.urls.size , formData);
  }
  update(size: Size) {
    const formData = new FormData();
    formData.append('id' , size.id.toString());
    formData.append('name' , size.name);
      return this.apiService.put<Size>(this.apiService.urls.size , formData);
  }

  delete(id: number): Observable<Size> {
    return this.apiService.delete<Size>(`${this.apiService.urls.size}/${id}`);
   }
}
