import { Detail } from './../../models/detail';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { DetailService } from './../../services/detail.service';
import { BillService } from './../../services/bill.service';
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Bill } from '../../models/bill';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {
  @ViewChild('detailModal', { static: true }) detailModal: ModalDirective;
  bills: Bill[] = [];
  anBill: Bill = {} as Bill;
  details: Detail[] = [];
  startDate: Date;
  firstName: string = '';
  endDate: Date;
  money = [];
 @Input() change = false;
  totalPrice: number = 0;

  constructor(private billService: BillService, private detailService: DetailService) { }

  ngOnInit(): void {
    this.dateNow();
  }

  sortName(event: Event) {
    this.change = !this.change;
    if (this.change === false) {
      event.preventDefault();
      this.bills.sort((a, b) => a.customer.firstName.localeCompare(b.customer.firstName));
    } else {
      event.preventDefault();
    this.bills.sort((a, b) => b.customer.firstName.localeCompare(a.customer.firstName));
    }
  }

  dateNow() {
    this.change = false;
    if (this.firstName === '') {
      this.firstName = 'abc';
    }
    this.billService.getBillDateNow(this.firstName).subscribe(res => {
      this.bills = res;
      if (this.firstName === 'abc') {
        this.firstName = '';
      }
      this.bills.sort((a, b) => a.customer.firstName.localeCompare(b.customer.firstName));
    });
  }

  getBillDate() {
    this.change = false;
    if (this.firstName === '') {
      this.firstName = 'abc';
    }
    this.billService.getBillDate(this.startDate, this.endDate, this.firstName).subscribe(res => {
      this.bills = res;
      if (this.firstName === 'abc') {
        this.firstName = '';
      }
      this.bills.sort((a, b) => a.customer.firstName.localeCompare(b.customer.firstName));

    });
  }

    // start detail
    showDetailModal(event: Event, billId: number) {
      this.totalPrice = 0;
      event.preventDefault();
      this.detailModal.show();
      this.detailService.getBillById(billId).subscribe(res => {
        this.details = res;
        this.money = [];
        for (let i = 0; i < res.length; i++) {
          this.money.push(res[i].quantity * res[i].product.price);
        }
        this.money.forEach(i => { this.totalPrice += i; });
      });
    }
    hideDetailModal() {
      this.detailModal.hide();
    }
    loadingDetail() {
      this.detailService.getAll().subscribe(res => {
        this.details = res;
      });
    }
    // end detail

}
