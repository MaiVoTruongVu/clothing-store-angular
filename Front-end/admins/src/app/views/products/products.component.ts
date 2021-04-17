import { ImageService } from './../../services/image.service';
import { Image } from '../../models/image';
import { ApiService } from './../../services/api.service';
import { PnotifyService } from './../../services/pnotify.service';
import { ProductService } from './../../services/product.service';
import { ProducerService } from './../../services/producer.service';
import { TypeService } from './../../services/type.service';
import { ColorService } from './../../services/color.service';
import { SizeService } from './../../services/size.service';
import { Producer } from './../../models/producer';
import { Product } from '../../models/product';
import { Type } from './../../models/type';
import { Color } from './../../models/color';
import { Size } from './../../models/size';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  @ViewChild('editModal', { static: true }) editModal: ModalDirective;
  @ViewChild('colorModal', { static: true }) colorModal: ModalDirective;
  // ViewChild dùng cho html gọi ra sử dụng
  @ViewChild('typeModal', { static: true }) typeModal: ModalDirective;
  @ViewChild('producerModal', { static: true }) producerModal: ModalDirective;
  @ViewChild('fileUpload', { static: false }) fileUpload: ElementRef;
  @ViewChild('filesUpload', { static: false }) filesUpload: ElementRef;
  styleColor: string;
  public imagePath: any;
  @Input() changePrice = false;
  imgURL: any;
  imgURLs = [];
  urls = [];
  p: number = 1;
  count: number = 10;
  products: Product[] = [];
  anProduct: Product = { size: {}, color: {}, type: {}, producer: {} } as Product;
  sizes: Size[] = [];
  anSize: Size = {} as Size;

  colors: Color[] = [];
  anColor: Color = {} as Color;

  // khai báo mảng Type rỗng
  types: Type[] = [];
  anType: Type = {} as Type;


  image: Image[] = [];
  anImage: Image = {} as Image;


  producers: Producer[] = [];
  anProducer: Producer = {} as Producer;
  setColor: string;
  code: string;
  searchTypeId: number;
  typeId: number = 0;
  check = false;
  frm: FormGroup;
  config: any;
  constructor(private sizeService: SizeService,
    private colorService: ColorService,
    private typeService: TypeService,
    private producerService: ProducerService,
    private productService: ProductService,
    private pnotify: PnotifyService,
    private fb: FormBuilder,
    private imageService: ImageService) {

      this.config = {
        itemsPerPage: 3,
        currentPage: 1,
        totalItems: this.products.length
      };
     }

    // phướng thức sẽ chạy đầu tiên khi khởi chạy chương trình
  ngOnInit(): void {
    this.loadingColor();
    this.loadingSize();
    this.loadingProducer();
    // gọi phương thức loadingType sẽ load dữ liệu từ api lên.
    this.loadingType();
    this.loadingProduct();
    this.loadForm();
  }

  loadForm() {
    this.frm = this.fb.group({
      code: [
        {value: this.anProduct.code},
        Validators.compose([Validators.required])
      ],
      name: [
        {value: this.anProduct.name},
        Validators.compose([Validators.required])
      ],
      price: [
        {value: this.anProduct.price},
        Validators.compose([Validators.required])
      ],
      gender: [
        {value: this.anProduct.gender},
        Validators.compose([Validators.required])
      ]
    });
   }

  // start product
  hideEditModal() {
    this.imgURLs = [];
    this.editModal.hide();
  }
  showEditModal(event: Event, id: number) {
    event.preventDefault();
    this.editModal.show();
    if (id === 0) {
      this.anProduct = {
        id: 0, isHot: false,
        size: { id: this.sizes[1].id },
        color: { id: 1 },
        type: { id: 1 },
        producer: { id: 1 }
      } as Product;
      this.imgURL = '';
      this.imgURLs = [];
      this.frm.reset();
      this.fileUpload.nativeElement.value = null;
    } else {
      this.productService.getId(id).subscribe(res => {
        this.anProduct = res;
        this.imgURL = res.avatar;
        this.imageService.getProductById(res.id).subscribe(it => {
         it.forEach(i => {
           this.imgURLs.push(i.url);
         });
        });
      });
    }
  }
  loadingProduct() {
    this.productService.getAll().subscribe(res => {
      this.products = res;
      res.sort((a, b) => a.name.localeCompare(b.name));
      res.sort((a, b) => a.price - b.price);
    });
  }
  pageChanged(event) {
    this.config.currentPage = event;
  }

  sortPrice(event: Event) {
    this.changePrice = !this.changePrice;
    if (this.changePrice === false) {
      event.preventDefault();
      this.products.sort((a, b) => a.price - b.price);
    } else {
      event.preventDefault();
      this.products.sort((a, b) => b.price - a.price);
    }
  }

  saveProduct() {
    if (this.anProduct.id === 0) {
      this.productService.add(this.anProduct, this.fileUpload.nativeElement.files[0]).subscribe(res => {
        if (Error) {
          // upload files
          this.imageService.add(res.id, this.filesUpload.nativeElement.files).subscribe(resImage => {
            this.pnotify.showSuccess('Thông báo', 'Thêm mới thành công !');
            this.hideEditModal();
            this.loadingProduct();
          });
        } else {
          this.pnotify.showFailure('Thông báo', 'Thêm mới thất bại !');
        }
        this.fileUpload.nativeElement.value = null;
      }, error => {
        if (error.status === 400) {
          this.pnotify.showFailure('Thông báo', 'Bạn cần thêm hình của sản phẩm !');
        }
      });
      this.fileUpload.nativeElement.value = null;
    } else {
      if (this.fileUpload.nativeElement.files[0] === undefined) {
        this.productService.updateNotFile(this.anProduct).subscribe(res => {
          if (Error) {
            this.imageService.add(res.id, this.filesUpload.nativeElement.files).subscribe(resImage => {
              this.pnotify.showSuccess('Thông báo', 'Cập nhật thành công !');
              this.hideEditModal();
              this.loadingProduct();
            });
          } else {
            this.pnotify.showFailure('Thông báo', 'Cập nhật thất bại !');
          }
        });
      } else {
        this.productService.update(this.anProduct, this.fileUpload.nativeElement.files[0]).subscribe(res => {
          if (Error) {
            // upload files
            this.imageService.add(res.id, this.filesUpload.nativeElement.files).subscribe(resImage => {
              this.pnotify.showSuccess('Thông báo', 'Cập nhật thành công !');
              this.hideEditModal();
              this.loadingProduct();
            });
          } else {
            this.pnotify.showFailure('Thông báo', 'Cập nhật thất bại !');
          }
          this.fileUpload.nativeElement.value = null;
        });
      }
    }
  }

  delete(e: Event, id: number) {
    e.preventDefault();
    this.pnotify.showConfirm('Thông báo', 'Bạn chắc chắn muốn xoá dòng dữ liệu này ?', yes => {
      if (yes) {
        this.productService.delete(id).subscribe(res => {
          if (Error) {
            this.pnotify.showSuccess('Thông báo', 'Xóa thành công !');
            this.loadingProduct();
          } else {
            this.pnotify.showFailure('Info', 'Xóa thất bại !');
          }
        });
      }
    });
  }

  searchCode() {
    if (this.code !== '') {
      this.productService.searchCode(this.code).subscribe(res => {
        this.products = res;
      });
    } else {
      this.loadingProduct();
    }
  }

  getFilter() {
    this.productService.searchType(this.searchTypeId).subscribe(res => {
      this.products = res;
    });
  }
  // end product

  // start size
  // loading data of size
  loadingSize() {
    this.sizeService.getAll().subscribe(res => {
      this.sizes = res;
      res.sort((a, b) => a.name.localeCompare(b.name));
    });
  }
  // end size

  // start color
  // loading data of color
  loadingColor() {
    this.colorService.getAll().subscribe(res => {
      this.colors = res;
    });
  }
  getColor() {
    const color = this.colors.find(res => res.id === this.anProduct.color.id);
    if (color) {
      return color.name;
    } else {
      return '';
    }
  }

  showColorModal() {
    this.anColor = { id: 0 } as Color;
    this.colorModal.show();
  }

  hideColorModal() {
    this.colorModal.hide();
  }

  saveColor() {
    if (this.anColor.id === 0) {
      this.colorService.add(this.anColor).subscribe(res => {
        if (Error) {
          this.pnotify.showSuccess('Thông báo', 'Thêm mới thành công !');
          this.anProduct.color.id = res.id;
          this.hideColorModal();
          this.loadingColor();
        } else {
          this.pnotify.showFailure('Thông báo', 'Thêm mới thất bại !');
        }
      });
    }
  }
  // end color


  // start producer
  // loading data of producer
  loadingProducer() {
    this.producerService.getAll().subscribe(res => {
      this.producers = res;
      res.sort((a, b) => a.name.localeCompare(b.name));
    });
  }
  showProducerModal() {
    this.anProducer = { id: 0 } as Producer;
    this.producerModal.show();
  }
  hideProducerModal() {
    this.producerModal.hide();
  }
  saveProducer() {
    if (this.anProducer.id === 0) {
      this.producerService.add(this.anProducer).subscribe(res => {
        if (Error) {
          this.pnotify.showSuccess('Thông báo', 'Thêm mới thành công !');
          this.anProduct.producer.id = res.id;
          this.hideProducerModal();
          this.loadingProducer();
        } else {
          this.pnotify.showFailure('Thông báo', 'Thêm mới thất bại !');
        }
      });
    }
  }
  // end producer


  // start type
  // loading data of type
  loadingType() {
    // lấy 1 mảng các đối tượng type
    this.typeService.getAll().subscribe(res => {
      // lưu dữ liệu trả về vào mảng rỗng
      this.types = res;
      res.sort((a, b) => a.name.localeCompare(b.name));
    });
  }
  // Xổ modal ra hiển thị cho người dùng thấy
  showTypeModal() {
    this.anType = { id: 0 } as Type;
    this.typeModal.show();
  }
  hideTypeModal() {
    this.typeModal.hide();
  }
  saveType() {
    if (this.anType.id === 0) {
      this.typeService.add(this.anType).subscribe(res => {
        if (Error) {
          this.pnotify.showSuccess('Thông báo', 'Thêm mới thành công !');
          this.anProduct.type.id = res.id;
          this.loadingType();
          this.hideTypeModal();
        } else {
          this.pnotify.showFailure('Thông báo', 'Thêm mới thất bại !');
        }
      });
    }
  }
  // end type

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

  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
        const filesAmount = event.target.files.length;
        for (let i = 0; i < filesAmount; i++) {
                const readers = new FileReader();
                // tslint:disable-next-line: no-shadowed-variable
                readers.onload = (event: any) => {
                   this.imgURLs.push(event.target.result);
                };

                readers.readAsDataURL(event.target.files[i]);
        }
    }
  }
  closeImageIndex(index: number) {
    this.imgURLs.splice(index , 1);
  }
}
