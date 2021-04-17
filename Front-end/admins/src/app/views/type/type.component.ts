import { ProductService } from './../../services/product.service';
import { PnotifyService } from './../../services/pnotify.service';
import { TypeService } from './../../services/type.service';
import { Product } from './../../models/product';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Component, OnInit, ViewChild, ChangeDetectorRef} from '@angular/core';
import { Type } from '../../models/type';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styleUrls: ['./type.component.css']
})
export class TypeComponent implements OnInit {
  @ViewChild('editModal', { static: true }) editModal: ModalDirective;
  types: Type[] = [];
  anType: Type = {} as Type;
  frm: FormGroup;
  action: string;
  products: Product[] = [];
  constructor(private typeService: TypeService,
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
    this.typeService.getAll().subscribe(res => {
      this.types = res;
      res.sort((a, b) => a.name.localeCompare(b.name));
      // this.typeService.sortName();
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
        { value: this.anType.name},
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
      this.anType = { id: 0 } as Type;
      this.action = 'Thêm loại hàng';
      this.frm.reset();
    } else {
      this.typeService.getId(id).subscribe(res => {
        this.anType = res;
        this.action = 'Chỉnh sửa';
      });
    }
  }

  save() {
    if (this.anType.id === 0) {
      this.typeService.add(this.anType).subscribe(res => {
        if (Error) {
          this.pnotify.showSuccess('Thông báo', 'Thêm mới thành công !');
          this.hideEditModal();
          this.loadingData();
        } else {
          this.pnotify.showFailure('Thông báo', 'Thêm mới thất bại !');
        }
      });
    } else {
      this.typeService.update(this.anType).subscribe(res => {
        if (Error) {
          this.pnotify.showSuccess('Thông báo', 'Cập nhật thành công !');
          this.hideEditModal();
          this.loadingData();
        } else {
          this.pnotify.showFailure('Thông báo', 'Cập nhật thất bại !');
        }
      });
    }
  }

  delete(e: Event, id: number) {
    e.preventDefault();
    this.pnotify.showConfirm('Thông báo', 'Bạn chắc chắn muốn xoá dòng dữ liệu này ?', yes => {
      if (yes) {
        for (let i = 0 ; i < this.products.length; i++) {
          if (this.products[i].type.id === id) {
                this.pnotify.showFailure('Thông báo', 'Loại hàng đã được sỡ hữu bởi sản phẩm !');
                return;
          }
        }
        this.typeService.delete(id).subscribe(res => {
          this.pnotify.showSuccess('Thông báo', 'Xóa thành công !');
           this.loadingData();
       });
      }
    });
  }
}
