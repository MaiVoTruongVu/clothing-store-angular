import { CartMake } from './../../models/cart-make';
import { Cart } from './../../models/cart';
import { ProductService } from './../../services/product.service';
import { Product } from './../../models/product';
import { CookieService } from 'ngx-cookie-service';
import { CartService } from './../../services/cart.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  //   quantity: number = 1;
  //   quantity2 = 1;
  //   sumPrice = 0;
  //   sumPrice2 = 0;
  //   price = 399000;
  //   price2 = 199000;
  //   total = 0;
  //   carts: Array<any> = [];
  totalPrice = 0;
  products: Product[] = [];
  carts: Cart[] = [];
  temp: CartMake[] = [];

  arrayPro = this.cookieService.get('shoppingCart');
  //   money: Array<number> = [];
  constructor(private cookieService: CookieService, private productService: ProductService, private cartService: CartService) { }

  ngOnInit(): void {
    // this.load();
    this.productService.getAll().subscribe(res => {
      res.forEach(it => {
        JSON.parse(this.arrayPro).forEach(s => {
          if (it.code === s.code) {
            this.temp.push({ quantity: s.quantity, product: it });
            this.totalPrice += it.price * s.quantity;
          }
        });
      });
    });
  }

  loadTemp(): any[] {
    return this.temp;
  }
  // tslint:disable-next-line: typedef
  load() {
    this.productService.getAll().subscribe(res => {
      res.forEach(it => {
        JSON.parse(this.arrayPro).forEach(s => {
          if (it.code === s.code) {
            this.temp.push({ quantity: s.quantity, product: it });
            this.totalPrice += it.price * s.quantity;
          }
        });
      });
    });
  }

  // tslint:disable-next-line: typedef
  plus(code: string) {
    this.totalPrice = 0;
    this.cookieService.set('shoppingCart' , JSON.stringify(this.cartService.add(code)));
    // location.reload();
    const index = this.temp.findIndex(it => it.product.code === code);
    if (index >= 0) {
      if (this.temp[index].quantity > 19) {
      } else {
        this.temp[index].quantity ++;
      }
    }
    this.temp.forEach(s => {
      this.totalPrice += s.product.price * s.quantity;
    });
  }

  // tslint:disable-next-line: typedef
  minius(code: string) {
    this.totalPrice = 0;
    this.cookieService.set('shoppingCart' , JSON.stringify(this.cartService.remove(code)));
    const index = this.temp.findIndex(it => it.product.code === code);
    if (index >= 0) {
      if (this.temp[index].quantity > 1) {
        this.temp[index].quantity --;
      } else {
        this.temp.splice(index , 1);
      }
    }
    this.temp.forEach(s => {
      this.totalPrice += s.product.price * s.quantity;
    });
  }
}
