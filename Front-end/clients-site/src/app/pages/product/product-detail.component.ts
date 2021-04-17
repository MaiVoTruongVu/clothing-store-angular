import { PnotifyService } from './../../services/pnotify.service';
import { CartService } from './../../services/cart.service';
import { CookieService } from 'ngx-cookie-service';
import { ImageService } from './../../services/image.service';
import { Image } from './../../models/image';
import { SizeService } from './../../services/size.service';
import { TypeService } from './../../services/type.service';
import { ProductService } from './../../services/product.service';
import { Product } from './../../models/product';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  products: Product[] = [];
  productsOfType: Product[] = [];
  anProduct: Product = { size: {}, color: {}, type: {}, producer: {} } as Product;
  images: Image[] = [];
  isCollapsed1 = false;
  isCollapsed2 = false;
  isCollapsed3 = false;
  y = 2;
  itemsPerSlide = 5;
  singleSlideOffset = true;
  noWrap = true;
  constructor(private productService: ProductService,
              private typeService: TypeService,
              private sizeService: SizeService,
              private route: ActivatedRoute,
              private imageService: ImageService,
              private router: Router,
              private cookieService: CookieService,
              private cartService: CartService,
              private pnotify: PnotifyService) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.loadingProductById(+id);
    this.loadingImgeByIdProduct(+id);
    this.findByProductOfType(+id);
  }
  // tslint:disable-next-line: typedef
  check1() {
    this.isCollapsed1 = !this.isCollapsed1;
    // if (this.isCollapsed2 === true || this.isCollapsed3 === true) {
    this.isCollapsed2 = false;
    this.isCollapsed3 = false;
    // }

  }
  // tslint:disable-next-line: typedef
  check2() {
    this.isCollapsed2 = !this.isCollapsed2;
    this.isCollapsed1 = false;
    this.isCollapsed3 = false;
  }
  // tslint:disable-next-line: typedef
  check3() {
    this.isCollapsed3 = !this.isCollapsed3;
    this.isCollapsed2 = false;
    this.isCollapsed1 = false;
  }

  // tslint:disable-next-line: typedef
  loadingProductById(id: number) {
    this.productService.getId(id).subscribe(res => {
      this.anProduct = res;
    });
  }
  // tslint:disable-next-line: typedef
  loadingImgeByIdProduct(id: number) {
    this.imageService.getProductById(id).subscribe(res => {
      this.images = res;
    });
  }

    // tslint:disable-next-line: typedef
    addCart(e: Event, code: string) {
      e.preventDefault();
      const dateNow = new Date();
      dateNow.setHours(dateNow.getHours() + 2);
      this.cookieService.set('shoppingCart' , JSON.stringify(this.cartService.add(code)), dateNow);
      this.pnotify.showSuccess('Thêm vào giỏ hàng thành công !');
    }

  // tslint:disable-next-line: typedef
  findByProductOfType(id: number) {
    this.productService.getId(id).subscribe(res => {
      this.productService.getAll().subscribe(it => {
          it.find(i => {
            if (i.id !== res.id && i.type.id === res.type.id) {
              if (this.productsOfType.length < 4) {
                this.productsOfType.unshift(i);
              } else {}
            }
          });
      });
    });
  }
}
