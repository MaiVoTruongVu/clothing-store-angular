import { Color } from './../models/color';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ColorService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Color]> {
    return this.apiService.get<[Color]>(this.apiService.urls.color);
  }

  getId(id: number): Observable<Color> {
    return this.apiService.get<Color>(`${this.apiService.urls.color}/${id}`);
  }

  add(color: Color) {
    const formData = new FormData();
    formData.append('name' , color.name);
      return this.apiService.post<Color>(this.apiService.urls.color , formData);
  }
  update(color: Color) {
    const formData = new FormData();
    formData.append('id' , color.id.toString());
    formData.append('name' , color.name);
      return this.apiService.put<Color>(this.apiService.urls.color , formData);
  }

  delete(id: number): Observable<Color> {
    return this.apiService.delete<Color>(`${this.apiService.urls.color}/${id}`);
   }
}
