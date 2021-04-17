import { Admin } from './models/admin';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import {tap} from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AppInterceptor implements HttpInterceptor {

 // tslint:disable-next-line: no-shadowed-variable
 constructor(private cookieService: CookieService , private router: Router) {}

 intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  //  const timeout = Number(request.headers.get('timeout')) || this.defaultTimeout;
   const inforStr = this.cookieService.get('login');
   if (inforStr) {
     const info = JSON.parse(inforStr) as Admin;

     const headers = new HttpHeaders({
       Authorization: `Bearer ${info.token}`
     });
     // clone sao chép lại tất cả token cộng thêm vào headers
     request = request.clone({headers});
   }
   return next.handle(request).pipe(
     tap(event => {
     }, error => {
       if (error.status === 401) {
         this.router.navigate(['/login']);
       } else if (error.status === 403) {
        this.router.navigate(['/error']);
      }
     }));
 }
}
// cách tạo interceptor -- ng g interceptor app
// cách tạo npm i ngx-cookie-service
