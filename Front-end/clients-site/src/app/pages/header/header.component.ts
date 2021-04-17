import { CartMake } from './../../models/cart-make';
import { CookieService } from 'ngx-cookie-service';
import { TypeService } from './../../services/type.service';
import { Type } from './../../models/type';
import { Product } from './../../models/product';
import { ProductService } from './../../services/product.service';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  name: string;
  products: Product[] = [];
  anProduct: Product = {} as Product;
  types: Type[] = [];
  quantity = 0;
  temp: CartMake[] = [];
  arrayPro = this.cookieService.get('shoppingCart');
  constructor(private productService: ProductService,
              private typeService: TypeService,
              private router: Router,
              private cookieService: CookieService) { }

  ngOnInit(): void {
    this.loadType();
    JSON.parse(this.arrayPro).forEach(s => {
      // this.temp.push(s);
      this.quantity += s.quantity;
    });
    // this.temp.forEach(res => {
    //   this.quantity += res.quantity;
    // });

    // console.log(this.temp);
  }
  // tslint:disable-next-line: typedef
  loadType() {
    this.typeService.getAll().subscribe(res => {
      this.types = res;
    });
  }

  // tslint:disable-next-line: typedef
  loadProduct() {
    this.productService.getAll().subscribe(pro => {
      // this.products = pro;
      this.router.navigate(['/product']);
    });
  }

  // tslint:disable-next-line: typedef
  searchName() {
    if (this.name === '') {
      this.loadProduct();
    } else {
      this.router.navigate(['/product-name', this.name]);
    }
  }




}
