import { Product } from '../models/product';
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

  updateNotFile(product: Product) {
    const formData = new FormData();
    formData.append('id', product.id.toString());
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
    return this.apiService.upadateFormData(this.apiService.urls.productNotFile, formData);
  }

  searchCode(code: string): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/searchCode/${code}`);
  }

  searchType(typeId: number): Observable<[Product]> {
    return this.apiService.get<[Product]>(`${this.apiService.urls.product}/searchType/${typeId}`);
  }

  delete(id: number): Observable<Product> {
    return this.apiService.delete<Product>(`${this.apiService.urls.product}/${id}`);
   }
}
