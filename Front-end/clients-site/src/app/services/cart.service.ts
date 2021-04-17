import { Cart } from './../models/cart';
import { CookieService } from 'ngx-cookie-service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  array: Cart[] = [];


  constructor(private cookieService: CookieService) { }


  // tslint:disable-next-line: typedef
  add(code: string): Cart[] {
    const ary = this.cookieService.get('shoppingCart');
    if (ary) {
      this.array = JSON.parse(ary) as Cart[];
    }
    const index = this.array.findIndex(it => it.code === code);
    if (index >= 0) {
      this.array[index].quantity ++;
    } else {
      this.array.push({code , quantity: 1});
    }
    return this.array;
  }

  // tslint:disable-next-line: typedef
  remove(code: string) {
    const ary = this.cookieService.get('shoppingCart');
    if (ary) {
      this.array = JSON.parse(ary) as Cart[];
    }
    const index = this.array.findIndex(it => it.code === code);
    if (index >= 0) {
      if (this.array[index].quantity > 1) {
        this.array[index].quantity --;
      } else {
        this.array.splice(index , 1);
      }
    } else {
      this.array.push({code , quantity: 1});
    }
    return this.array;
  }

}
