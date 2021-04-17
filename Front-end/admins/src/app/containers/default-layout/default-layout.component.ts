import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import {Component} from '@angular/core';
import { navItems } from '../../_nav';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;
  public navItems = navItems;
  constructor(private cookie: CookieService, private router: Router) {}

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  logout() {
    this.cookie.delete('login', '/', 'localhost');
    this.router.navigate(['/dashboard']);
  }
}
