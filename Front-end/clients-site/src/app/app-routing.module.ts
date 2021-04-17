import { CheckoutComponent } from './pages/checkout/checkout.component';
import { CartComponent } from './pages/cart/cart.component';
import { ProductDetailComponent } from './pages/product/product-detail.component';
import { ProductComponent } from './pages/product/product.component';
import { HomeComponent } from './pages/home/home.component';
import { DefultLayoutComponent } from './layout/defult-layout/defult-layout.component';
import { LoginComponent } from './pages/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path : 'login' , component : LoginComponent},
  {
    path : '' , component : DefultLayoutComponent ,
    children : [
      { path : 'home' , component : HomeComponent},
      { path : '' , component : HomeComponent},
      { path : 'product' , component : ProductComponent},
      { path : 'product-name/:value' , component : ProductComponent},
      { path : 'gender/:value' , component : ProductComponent},
      {path : 'product-detail/:id' , component: ProductDetailComponent},
      { path : 'cart' , component : CartComponent},
      { path : 'checkout' , component : CheckoutComponent},
      { path : 'product-type/:value' , component : ProductComponent},
    ]
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
