import { Type } from './../models/type';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Type]> {
    return this.apiService.get<[Type]>(this.apiService.urls.type);
  }
  getId(id: number): Observable<Type> {
    return this.apiService.get<Type>(`${this.apiService.urls.type}/${id}`);
  }

  add(type: Type) {
    const formData = new FormData();
    formData.append('name' , type.name);
      return this.apiService.post<Type>(this.apiService.urls.type , formData);
  }
  update(type: Type) {
    const formData = new FormData();
    formData.append('id' , type.id.toString());
    formData.append('name' , type.name);
      return this.apiService.put<Type>(this.apiService.urls.type , formData);
  }

  delete(id: number): Observable<Type> {
    return this.apiService.delete<Type>(`${this.apiService.urls.type}/${id}`);
   }
   sortName():  Observable<[Type]> {
     return this.apiService.get<[Type]>(`${this.apiService.urls.type}/sort`);
   }
}
