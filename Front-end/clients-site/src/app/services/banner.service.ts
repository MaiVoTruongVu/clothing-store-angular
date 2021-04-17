import { Banner } from '../models/banner';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BannerService {
  anBanner: Banner = {} as Banner;
  constructor(private api: ApiService) { }

  add(ban , file) {
    const formData = new FormData();
    formData.append('url', file);
    formData.append('title', ban.title);
    formData.append('content', ban.content);
    return this.api.addFormData(this.api.urls.banner, formData);
   }

   getAll(): Observable<[Banner]> {
     return this.api.get<[Banner]>(this.api.urls.banner);
   }

   getId(id: number): Observable<Banner> {
     return this.api.get<Banner>(`${this.api.urls.banner}/${id}`);
   }

   delete(id: number): Observable<Banner> {
    return this.api.delete<Banner>(`${this.api.urls.banner}/${id}`);
   }

   update(ban , file) {
    const formData = new FormData();
    formData.append('id' , ban.id);
    formData.append('url', file);
    formData.append('title', ban.title);
    formData.append('content', ban.content);
    return this.api.upadateFormData(this.api.urls.banner, formData);
   }
}
