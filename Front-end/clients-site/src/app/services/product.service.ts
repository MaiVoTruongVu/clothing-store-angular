import { Product } from './../models/product';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Product]> {
    return this.apiService.get<[Product]>(this.apiService.urls.product);
  }

  getId(id: number): Observable<Product> {
    return this.apiService.get<Product>(`${this.apiService.urls.product}/${id}`);
  }

  // tslint:disable-next-line: typedef
  add(product: Product, file) {
    const formData = new FormData();
    formData.append('avatar', file);
    formData.append('code', product.code);
    formData.append('name', product.name);
    formData.append('price', product.price.toString());
    formData.append('gender', product.gender.toString());
    formData.append('description', product.description);
    formData.append('type', product.type.id.toString());
    formData.append('size', product.size.id.toString());
    formData.append('producer', product.producer.id.toString());
    formData.append('color', product.color.id.toString());
    formData.append('isHot', product.isHot.toString());
    return this.apiService.addFormData(this.apiService.urls.product, formData);
  }

  // tslint:disable-next-line: typedef
  update(product: Product, file) {
    const formData = new FormData();
    formData.append('id', product.id.toString());
    formData.append('avatar', file);
    formData.append('code', product.code);
    formData.append('name', product.name);
    formData.append('price', product.price.toString());
    formData.append('gender', product.gender.toString());
    formData.append('description', product.description);
    formData.append('type', product.type.id.toString());
    formData.append('size', product.size.id.toString());
    formData.append('producer', product.producer.id.toString());
    formData.append('color', product.color.id.toString());
    formData.append('isHot', product.isHot.toString());
    return this.apiService.upadateFormData(this.apiService.urls.product, formData);
  }

  searchCode(code: string): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/searchCode/${code}`);
  }

  searchName(name: string): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/searchName/${name}`);
  }

  getType(typeId: number): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/searchType/${typeId}`);
  }

  getGender(gender: string): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/gender/${gender}`);
  }

  // getGenderAndType(gender: string , typeId: number): Observable<[Product]> {
  //   return this.apiService.get<[Product]>(`${this.apiService.urls.product}/gender/${gender}/type/${typeId}`);
  // }

 getSize(sizeId: number): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/size/${sizeId}`);
  }
  getColor(sizeId: number): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/searchColor/${sizeId}`);
  }

  delete(id: number): Observable<Product> {
    return this.apiService.delete<Product>(`${this.apiService.urls.product}/${id}`);
   }
  getMinMaxPrice(min: number , max: number): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/searchMinMax/${min}/${max}`);
  }
}
