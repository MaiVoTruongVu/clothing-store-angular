import { Detail } from './../../models/detail';
import { DetailService } from './../../services/detail.service';
import { Component, OnInit, Input } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-statistical',
  templateUrl: './statistical.component.html',
  styleUrls: ['./statistical.component.css']
})
export class StatisticalComponent implements OnInit {
  startDate: Date;
  endDate: Date;
  firstName: string = '';
  details: Detail[] = [];
  totalPrice = 0;
  sumQuantity: number = 0;
  @Input() change = false;
  @Input() changePrice = false;
  @Input() changeCus = false;
  constructor(private detailService: DetailService, private customerService: CustomerService) { }

  ngOnInit(): void {
    this.dateNow();
  }

  sortQuantity(event: Event) {
    this.change = !this.change;
    if (this.change === false) {
      event.preventDefault();
      this.details.sort((a, b) => a.quantity - b.quantity);
    } else {
      event.preventDefault();
      this.details.sort((a, b) => b.quantity - a.quantity);
    }
  }
  sortName(event: Event) {
    this.changeCus = !this.changeCus;
    if (this.changeCus === false) {
      event.preventDefault();
      this.details.sort((a, b) => a.bill.customer.firstName.localeCompare(b.bill.customer.firstName));
    } else {
      event.preventDefault();
    this.details.sort((a, b) => b.bill.customer.firstName.localeCompare(a.bill.customer.firstName));
    }
  }

  sortPrice(event: Event) {
    this.changePrice = !this.changePrice;
    if (this.changePrice === false) {
      event.preventDefault();
      this.details.sort((a, b) => a.price - b.price);
    } else {
      event.preventDefault();
      this.details.sort((a, b) => b.price - a.price);
    }
  }

  satisticByDate() {
    this.change = false;
    if (this.firstName === '') {
      this.firstName = 'abc';
    }
    this.detailService.statisticDetail(this.startDate, this.endDate, this.firstName).subscribe(res => {
      res.sort((a, b) => a.quantity - b.quantity);
      this.totalPrice = 0;
      this.sumQuantity = 0;
        res.forEach(it => {
          this.totalPrice += it.quantity * it.price;
          this.sumQuantity += it.quantity;
        });
      this.details = res;
      if (this.firstName === 'abc') {
        this.firstName = '';
      }
    });
  }

  dateNow() {
    this.change = false;
    if (this.firstName === '') {
      this.firstName = 'abc';
    }
    this.detailService.dateNow(this.firstName).subscribe(res => {
      res.sort((a, b) => a.quantity - b.quantity);
      this.totalPrice = 0;
      this.sumQuantity = 0;
      res.forEach(it => {
        this.totalPrice += it.quantity * it.price;
        this.sumQuantity += it.quantity;
      });
      this.details = res;
      if (this.firstName === 'abc') {
        this.firstName = '';
      }
    });
  }
}
