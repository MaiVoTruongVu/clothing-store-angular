import { ProductService } from './../../services/product.service';
import { Product } from './../../models/product';
import { PnotifyService } from './../../services/pnotify.service';
import { ProducerService } from './../../services/producer.service';
import { Producer } from './../../models/producer';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
// import { error } from 'protractor';

@Component({
  selector: 'app-producer',
  templateUrl: './producer.component.html',
  styleUrls: ['./producer.component.css']
})
export class ProducerComponent implements OnInit {
  @ViewChild('editModal', { static: true }) editModal: ModalDirective;
  producers: Producer[] = [];
  anProducer: Producer = {} as Producer;
  frm: FormGroup;
  action: string;
  products: Product[] = [];
  page: number = 1;
  count: number = 5;
  constructor(private producerService: ProducerService,
              private pnotify: PnotifyService,
              private fb: FormBuilder,
              private cd: ChangeDetectorRef,
              private productService: ProductService) { }

  ngOnInit(): void {
    this.loadingData();
    this.loadForm();
    this.loadingProduct();
  }

  loadingData() {
    this.producerService.getAll().subscribe(res => {
      this.producers = res;
      res.sort((a, b) => a.name.localeCompare(b.name));
    });
  }

  loadingProduct() {
    this.productService.getAll().subscribe(res => {
      this.products = res;
    });
  }

  loadForm() {
    this.frm = this.fb.group({
      name: [
        { value: this.anProducer.name },
        Validators.compose([Validators.required])
      ],
      phone: [
        { value: this.anProducer.phone},
        Validators.compose([Validators.required]),
      ],
      address: [
        { value: this.anProducer.address},
        Validators.compose([Validators.required])
      ]
    });
  }
  hideEditModal() {
    this.editModal.hide();
  }
  showEditModal(event: Event, id: number) {
    event.preventDefault();
    this.editModal.show();
    if (id === 0) {
      this.anProducer = { id: 0 } as Producer;
      this.action = 'Th??m nh?? s???n xu???t';
      this.frm.reset();
    } else {
      this.producerService.getId(id).subscribe(res => {
        this.anProducer = res;
        this.action = 'Ch???nh s???a';
      });
    }
  }

  save() {
    if (this.anProducer.id === 0) {
      this.producerService.add(this.anProducer).subscribe(res => {
        if (Error) {
          this.pnotify.showSuccess('Th??ng b??o', 'Th??m m???i th??nh c??ng !');
          this.hideEditModal();
          this.loadingData();
        } else {
          this.pnotify.showFailure('Th??ng b??o', 'Th??m m???i th???t b???i !');
        }
      });
    } else {
      this.producerService.update(this.anProducer).subscribe(res => {
        if (Error) {
          this.pnotify.showSuccess('Th??ng b??o', 'C???p nh???t th??nh c??ng !');
          this.hideEditModal();
          this.loadingData();
        } else {
          this.pnotify.showFailure('Th??ng b??o', 'C???p nh???t th???t b???i !');
        }
      });
    }
  }

  delete(e: Event, id: number) {
    e.preventDefault();
    this.pnotify.showConfirm('Th??ng b??o', 'B???n ch???c ch???n mu???n xo?? d??ng d??? li???u n??y ?', yes => {
      if (yes) {
        for (let i = 0 ; i < this.products.length; i++) {
          if (this.products[i].producer.id === id) {
                this.pnotify.showFailure('Th??ng b??o', 'Nh?? s???n xu???t ???? ???????c s??? h???u b???i s???n ph???m !');
                return;
          }
        }
        this.producerService.delete(id).subscribe(res => {
          this.pnotify.showSuccess('Th??ng b??o', 'X??a th??nh c??ng !');
           this.loadingData();
       });
      }
    });
  }

}
