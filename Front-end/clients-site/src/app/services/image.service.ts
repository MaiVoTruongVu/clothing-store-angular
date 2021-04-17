import { Image } from './../models/image';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  files: File[] = [];

  constructor(private apiService: ApiService) { }

  // tslint:disable-next-line: typedef
  add(id , files) {
    const formData = new FormData();
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i]);
    }
    formData.append('product', id);
    return this.apiService.addFormData(`${this.apiService.urls.image}/uploadfileImage`, formData);
   }

   getProductById(id: number): Observable<[Image]> {
     return this.apiService.get<[Image]>(`${this.apiService.urls.image}/getProductById/${id}`);
   }

   getAll(): Observable<[Image]> {
     return this.apiService.get<[Image]>(this.apiService.urls.image);
   }
}
