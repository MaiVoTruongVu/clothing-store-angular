import { PnotifyService } from './../../services/pnotify.service';
import { CookieService } from 'ngx-cookie-service';
import { Cart } from './../../models/cart';
import { CartService } from './../../services/cart.service';
import { Color } from './../../models/color';
import { ColorService } from './../../services/color.service';
import { SizeService } from './../../services/size.service';
import { TypeService } from './../../services/type.service';
import { ProductService } from './../../services/product.service';
import { Banner } from './../../models/banner';
import { Size } from './../../models/size';
import { Type } from './../../models/type';
import { Product } from './../../models/product';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  products: Product[] = [];
  products2: Product[] = [];
  anProduct: Product = {} as Product;
  types: Type[] = [];
  anType: Type = {} as Type;
  sizes: Size[] = [];
  banners: Banner[] = [];
  colors: Color[] = [];
  anColor: Color = {} as Color;
  carts: Cart[] = [];
  anCart: Cart = {} as Cart;
  quantity = 1;
  constructor(private productService: ProductService,
              private typeService: TypeService,
              private sizeService: SizeService,
              private colorService: ColorService,
              private route: ActivatedRoute,
              private cookieService: CookieService,
              private cartService: CartService,
              private pnotify: PnotifyService) { }

  ngOnInit(): void {
    this.loadingType();
    this.loadingSize();
    this.loadingColor();
    // real time data
    // dùng lọc dữ liệu gender and type không cần reset
    this.route.params.subscribe(routeParam => {
      if (routeParam.value === undefined) {
        this.loadingProduct1();
      } else {
        if (isNaN(routeParam.value)) {
          if (routeParam.value === 'Nam') {
            this.loadingGenderByProduct(routeParam.value);
          } else if ( routeParam.value === 'Nu') {
            this.loadingGenderByProduct(routeParam.value);
          }
           else {
            this.searchName(routeParam.value);
          }
        } else {
          this.loadingTypeByProduct(+routeParam.value);
        }
      }
    });

  }
  // tslint:disable-next-line: typedef
  loadingTypeByProduct(id: number) {
    this.productService.getType(id).subscribe(res => {
      this.products = res;
    });
  }
  // tslint:disable-next-line: typedef
  loadingProduct1() {
    this.productService.getAll().subscribe(res => {
      res.forEach(it => {
        if (this.products.length < 6) {
          this.products.push(it);
        }
      });
    });
  }

  // tslint:disable-next-line: typedef
  addMove() {
    this.productService.getAll().subscribe(res => {
      const max = this.products.length;
      for (let i = this.products.length ; i < res.length && i < max + 6; i++) {
        if (this.products.length < this.products.length + this.products.length) {
          this.products.push(res[i]);
        } else {}
      }
    });
  }
  // tslint:disable-next-line: typedef
  loadingGenderByProduct(g: string) {
    this.productService.getGender(g).subscribe(res => {
      // this.products.pop();
      this.products = res;
    });
  }
  // tslint:disable-next-line: typedef
  loadingType() {
    this.typeService.getAll().subscribe(res => {
      this.types = res;
    });
  }
  // tslint:disable-next-line: typedef
  loadingSize() {
    this.sizeService.getAll().subscribe(res => {
      this.sizes = res;
    });
  }
  // tslint:disable-next-line: typedef
  getSize(id: number) {
    this.productService.getSize(id).subscribe(res => {
      this.products = [];
      this.products = res;
    });
  }
  // tslint:disable-next-line: typedef
  getType(e, id: number) {
    e.preventDefault();
    this.productService.getType(id).subscribe(res => {
      this.products = [];
      this.products = res;
    });
  }

  // tslint:disable-next-line: typedef
  searchName(name: string) {
    if (name === '') {
      return;
    } else {
      this.productService.searchName(name).subscribe(it => {
        this.products = [];
        this.products = it;
  });
    }
  }
  // tslint:disable-next-line: typedef
  loadingColor() {
    this.colorService.getAll().subscribe(res => {
      this.colors = res;
    });
  }
  // tslint:disable-next-line: typedef
  getColor(id: number) {
    this.productService.getColor(id).subscribe(res => {
      this.products = [];
      this.products = res;
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

  // tslint:disable-next-line: typedef
  getMinMaxPrice(min: number, max: number) {
    this.productService.getMinMaxPrice(min, max).subscribe(res => {
      this.products = res;
      console.log(res);
    });
  }

}
