import { DetailService } from './../../services/detail.service';
import { Detail } from './../../models/detail';
import { BillService } from './../../services/bill.service';
import { Bill } from './../../models/bill';
import { PnotifyService } from './../../services/pnotify.service';
import { CustomerService } from './../../services/customer.service';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { Customer } from '../../models/customer';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  @ViewChild('editModal', { static: true }) editModal: ModalDirective;
  @ViewChild('billModal', { static: true }) billModal: ModalDirective;
  @ViewChild('detailModal', { static: true }) detailModal: ModalDirective;
  @ViewChild('editBillModal', { static: true }) editBillModal: ModalDirective;
  customers: Customer[] = [];
  anCustomer: Customer = {} as Customer;
  bills: Bill[] = [];
  anBill: Bill = { customer: {} } as Bill;
  details: Detail[] = [];
  anDetail: Detail = { bill: {}, product: {} } as Detail;
  firstName: string;
  lastName: string;
  phone: string;
  email: string;
  totalPrice: number = 0;
  money: number[] = [];
  page: number = 0;
  count: number = 2;
  @Input() check = true;

  constructor(private cusService: CustomerService,
    private pnotify: PnotifyService,
    private billService: BillService,
    private detailService: DetailService) { }

  ngOnInit(): void {
    this.loadingCustomer();
    this.loadingBill();
    this.loadingDetail();
  }
  loadingCustomer() {
    this.cusService.getAll().subscribe(res => {
      this.customers = res;
      res.forEach(it => {
        if (it.address === 'undefined') {it.address = 'Không có địa chỉ nơi ở'; }
      });
    });
  }
  hideEditModal() {
    this.editModal.hide();
  }
  showEditModal(event: Event, id: number) {
    event.preventDefault();
    this.editModal.show();
    this.cusService.getId(id).subscribe(res => {
      this.anCustomer = res;
      if (res.address === 'undefined') {res.address = 'Không có địa chỉ nơi'; }
    });
  }
  delete(e: Event, id: number) {
    e.preventDefault();
    this.pnotify.showConfirm('Thông báo', 'Bạn chắc chắn muốn xoá dòng dữ liệu này ?', yes => {
      if (yes) {
        for (let i = 0; i < this.bills.length; i++) {
          if (this.bills[i].customer.id === id) {
            this.pnotify.showFailure('Thông báo', 'Khách hàng này đã có một hoặc nhiều hóa đơn !');
            return;
          }
        }
        this.cusService.delete(id).subscribe(res => {
          this.pnotify.showSuccess('Thông báo', 'Xóa thành công !');
          this.loadingCustomer();
        });
      }
    });
  }
  searchName() {
    if (this.firstName !== '') {
      this.cusService.searchName(this.firstName).subscribe(res => {
        this.customers = res;
      });
    } else {
      this.loadingCustomer();
    }
  }
  sortName(event: Event) {
    event.preventDefault();
    this.check = !this.check;
    if (this.check === true) {
      this.customers.sort((a, b) => a.firstName.localeCompare(b.firstName));
    } else {
      this.customers.sort((a, b) => b.firstName.localeCompare(a.firstName));
    }
  }
  // start bill
  loadingBill() {
    this.billService.getAll().subscribe(res => {
      this.bills = res;
    });
  }

  hideBillModal() {
    this.billModal.hide();
  }
  showBillModal(event: Event, customerId: number) {
    event.preventDefault();
    this.billModal.show();
    this.billService.getCustomerById(customerId).subscribe(res => {
      this.bills = res;
    });
  }

  showEditBillModal(e, id: number) {
    e.preventDefault();
    this.editBillModal.show();
    this.billService.getId(id).subscribe(res => {
      this.anBill = res;
    });
  }
  hideEditBillModal() {
    this.editBillModal.hide();
  }

  saveBill() {
    this.billService.update(this.anBill).subscribe(res => {
      if (Error) {
        this.pnotify.showSuccess('Thông báo', 'Cập nhật thành công !');
        this.hideEditBillModal();
        this.billService.getCustomerById(res.customer.id).subscribe(it => {
          this.bills = it;
        });
      } else {
        this.pnotify.showFailure('Thông báo', 'Cập nhật thất bại !');
      }
    });
  }
  // end bill

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
