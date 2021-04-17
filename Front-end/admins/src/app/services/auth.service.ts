import { Admin } from './../models/admin';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private api: ApiService) { }
  login(un: string , pwd: string): Observable<Admin> {
    const data = {
      username: un,
      password: pwd
    };
    return this.api.postLogin<Admin>(this.api.urls.login , data);
  }
}
