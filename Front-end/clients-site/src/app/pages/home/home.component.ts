import { CartService } from './../../services/cart.service';
import { PnotifyService } from './../../services/pnotify.service';
import { CookieService } from 'ngx-cookie-service';
import { ProductService } from './../../services/product.service';
import { Banner } from './../../models/banner';
import { BannerService } from './../../services/banner.service';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  banners: Banner[] = [];
  productsIsHot: Product[] = [];

  constructor(private bannerService: BannerService,
              private productService: ProductService,
              private cookieService: CookieService,
              private pnotify: PnotifyService,
              private cartService: CartService) { }

  ngOnInit(): void {
    this.bannerService.getAll().subscribe(res => {
      this.banners = res;
    });
    this.productService.getAll().subscribe(it => {
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < it.length; i++) {
        if (it[i].isHot) {
          if (this.productsIsHot.length < 8) {
            this.productsIsHot.push(it[i]);
          } else {
            break;
          }
        }
      }
    });
  }

  // tslint:disable-next-line: typedef
  addCart(e: Event, code: string) {
    // tslint:disable-next-line: prefer-const
    e.preventDefault();
    const dateNow = new Date();
    dateNow.setHours(dateNow.getHours() + 2);
    this.cookieService.set('shoppingCart', JSON.stringify(this.cartService.add(code)), dateNow);
    this.pnotify.showSuccess('Thêm vào giỏ hàng thành công !');
  }

}
