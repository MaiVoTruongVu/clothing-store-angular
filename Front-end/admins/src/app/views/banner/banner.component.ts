import { PnotifyService } from './../../services/pnotify.service';
import { Bannerervice } from './../../services/banner.service';
import { Component, OnInit, ViewChild, ElementRef, ChangeDetectorRef } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Banner } from '../../models/banner';
import { config } from 'process';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {
  @ViewChild('editModal', { static: true }) editModal: ModalDirective;
  @ViewChild('fileUpload', { static: false }) fileUpload: ElementRef;
  anBanner: Banner = {} as Banner;
  banners: Banner[] = [];
  public imagePath: any;
  frm: FormGroup;
  imgURL: any;
  action: string = '';
  // url: string = this.fileUpload.nativeElement.files[0];
  constructor(private bannerervice: Bannerervice, private fb: FormBuilder,
    private cd: ChangeDetectorRef, private pnotifyService: PnotifyService) { }

  ngOnInit(): void {
    this.loadingBanner();
    this.loadForm();
  }


  loadForm() {
    this.frm = this.fb.group({
      title: [
        { value: this.anBanner.title },
        Validators.compose([Validators.required])
      ],
      content: [
        { value: this.anBanner.content },
        Validators.compose([Validators.required])
      ]
    });
  }

  loadingBanner() {
    this.bannerervice.getAll().subscribe(res => {
      this.banners = res;
    });
  }
  hideEditModal() {
    this.editModal.hide();
  }
  showEditModal(event: Event, id: number) {
    event.preventDefault();
    this.editModal.show();
    if (id === 0) {
      this.action = 'Thêm mới banner';
      this.anBanner = { id: 0 } as Banner;
      this.imgURL = '';
      this.frm.reset();
    } else {
      console.log(this.fileUpload.nativeElement.files[0]);
      this.fileUpload.nativeElement.value = null;
      this.bannerervice.getId(id).subscribe(res => {
        this.anBanner = res;
        this.imgURL = res.url;
        console.log(res);
        this.action = 'Chỉnh sửa banner';
        console.log(res.url);
      });
    }
  }
  // action save banner
  save() {
    if (this.anBanner.id === 0) {
      this.bannerervice.add(this.anBanner, this.fileUpload.nativeElement.files[0]).subscribe(res => {
        if (Error) {
          this.pnotifyService.showSuccess('Thông báo', 'Thêm mới thành công !');
          this.hideEditModal();
          this.loadingBanner();
        } else {
          this.pnotifyService.showFailure('Thông báo', 'Thêm mới thất bại !');
        }
        this.fileUpload.nativeElement.value = null;
      // tslint:disable-next-line: no-shadowed-variable
      }, error => {
        if (error.status === 400) {
          this.pnotifyService.showFailure('Thông báo', 'Bạn cần thêm hình của banner !');
        }
      });
    } else {
      if (this.fileUpload.nativeElement.files[0] === undefined) {
        console.log(this.fileUpload.nativeElement.files[0]);
        this.bannerervice.updateNotFile(this.anBanner).subscribe(res => {
          if (Error) {
            this.pnotifyService.showSuccess('Thông báo', 'Cập nhật thành công !');
            this.hideEditModal();
            this.loadingBanner();
          } else {
            this.pnotifyService.showFailure('Thông báo', 'Cập nhật thất bại !');
          }
        });
      }
      this.bannerervice.update(this.anBanner, this.fileUpload.nativeElement.files[0]).subscribe(res => {
        if (Error) {
          this.pnotifyService.showSuccess('Thông báo', 'Cập nhật thành công !');
          this.hideEditModal();
          this.loadingBanner();
        } else {
          this.pnotifyService.showFailure('Thông báo', 'Cập nhật thất bại !');
        }
        this.fileUpload.nativeElement.value = null;
      });
    }
  }
  // show image when to change input
  preview(fileUpload: any) {
    if (fileUpload.length === 0) {
      return;
    }
    // tslint:disable-next-line: prefer-const
    let reader = new FileReader();
    this.imagePath = fileUpload;
    reader.readAsDataURL(this.fileUpload.nativeElement.files[0]);
    // tslint:disable-next-line: variable-name
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    };
  }

  delete(e: Event, id: number) {
    e.preventDefault();
    this.pnotifyService.showConfirm('Thông báo', 'Bạn chắc chắn muốn xoá dòng dữ liệu này ?', yes => {
      if (yes) {
        this.bannerervice.delete(id).subscribe(res => {
          if (Error) {
            this.pnotifyService.showSuccess('Thông báo', 'Xóa thành công !');
            this.loadingBanner();
          } else {
            this.pnotifyService.showFailure('Info', 'Xóa thất bại !');
          }
        });
      }
    });
  }
}
