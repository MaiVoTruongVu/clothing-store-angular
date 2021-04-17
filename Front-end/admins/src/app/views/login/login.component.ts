import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent {
  message: string;
  username: string;
  password: string;


  constructor(private authService: AuthService , private router: Router , private cookieService: CookieService) { }

  // tslint:disable-next-line: use-lifecycle-interface
  ngOnInit(): void {
  }

  login() {
    const dateNow = new Date();
    dateNow.setDate(dateNow.getDate() + 2);
    this.authService.login(this.username, this.password).subscribe(res => {
      this.cookieService.set('login' , JSON.stringify(res), dateNow),
        this.router.navigate(['/dashboard']);
    }, err => {
      this.message = 'Tên đăng nhập hoặc mật khẩu của bạn không đúng !';
  }
    );
  }
}
