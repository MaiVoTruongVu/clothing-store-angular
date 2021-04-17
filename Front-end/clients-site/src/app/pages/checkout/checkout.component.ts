import { PnotifyService } from './../../services/pnotify.service';
import { Bill } from './../../models/bill';
import { Detail } from './../../models/detail';
import { Customer } from './../../models/customer';
import { CustomerService } from './../../services/customer.service';
import { DetailService } from './../../services/detail.service';
import { BillService } from './../../services/bill.service';
import { CookieService } from 'ngx-cookie-service';
import { ProductService } from './../../services/product.service';
import { CartMake } from './../../models/cart-make';
import { Product } from './../../models/product';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { AlertComponent } from 'ngx-bootstrap/alert';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  totalPrice = 0;
  products: Product[] = [];
  temp: CartMake[] = [];
  anCustomer: Customer = {} as Customer;
  anProduct: Product = {} as Product;
  details: Detail[] = [];
  anDetail: Detail = {} as Detail;
  anBill: Bill = {} as Bill;
  frm: FormGroup;
  alerts: any[] = [{
    type: '',
    msg: ``,
    timeout: 2000
  }];
  arrayPro = this.cookieService.get('shoppingCart');
  constructor(private cookieService: CookieService,
              private productService: ProductService,
              private billService: BillService,
              private detailService: DetailService,
              private cusService: CustomerService,
              private pnotify: PnotifyService,
              private fb: FormBuilder,
              private cd: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.loadingCart();
    this.loadForm();
  }

  // tslint:disable-next-line: typedef
  loadForm() {
    this.frm = this.fb.group({
      lastName: [
        { value: this.anCustomer.lastName},
        Validators.compose([Validators.required])
      ],
      firstName: [
        { value: this.anCustomer.firstName},
        Validators.compose([Validators.required])
      ],
      phone: [
        { value: this.anCustomer.phone},
        Validators.compose([Validators.required])
      ],
      email: [
        { value: this.anCustomer.email},
        Validators.compose([Validators.required, Validators.email])
      ],
      deliveryAddress: [
        { value: this.anBill.deliveryAddress},
        Validators.compose([Validators.required])
      ]
    });
  }

  onClosed(dismissedAlert: AlertComponent): void {
    this.alerts = this.alerts.filter(alert => alert !== dismissedAlert);
  }

  // tslint:disable-next-line: typedef
  loadingCart() {
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
  save() {
    this.cusService.add(this.anCustomer).subscribe(cus => {
      this.billService.add(cus.id, this.anBill).subscribe(bill => {
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.temp.length; i++) {
          this.detailService.add(bill.id,
            this.temp[i].product.id,
            this.temp[i].quantity,
            this.temp[i].product.price).subscribe(detail => {
              this.anDetail = detail;
            });
        }
      });
    });
    this.pnotify.showSuccess('Bạn đã đặt hàng thành công!');
    // this.alerts.push({
    //   type: 'success',
    //   msg: `Bạn đã đặt hàng thành công!`,
    //   timeout: 2000
    // });
  }

}
