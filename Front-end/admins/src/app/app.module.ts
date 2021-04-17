import { AppInterceptor } from './app.interceptor';
import { CookieService } from 'ngx-cookie-service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
// import {LoadingBarModule} from 'ngx-loading-bar';



const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppComponent } from './app.component';

// Import containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';

const APP_CONTAINERS = [
  DefaultLayoutComponent
];

import {
  AppAsideModule,
  AppBreadcrumbModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule,
} from '@coreui/angular';

// Import routing module
import { AppRoutingModule } from './app.routing';

// Import 3rd party components
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ChartsModule } from 'ng2-charts';
import { CustomersComponent } from './views/customers/customers.component';
import { ProductsComponent } from './views/products/products.component';
import { ProducerComponent } from './views/producer/producer.component';
import { BannerComponent } from './views/banner/banner.component';
import { TextareaComponent } from './controls/textarea/textarea.component';
import { InputComponent } from './controls/input/input.component';
import { DemoComponent } from './demo/demo/demo.component';
import { BillComponent } from './views/bill/bill.component';
import { DetailComponent } from './views/detail/detail.component';
import { TypeComponent } from './views/type/type.component';
import { StatisticalComponent } from './views/statistical/statistical.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { ErrorComponent } from './views/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    AppAsideModule,
    AppBreadcrumbModule.forRoot(),
    AppFooterModule,
    AppHeaderModule,
    AppSidebarModule,
    PerfectScrollbarModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ChartsModule,
    TabsModule.forRoot(),
    ModalModule.forRoot(),
    NgxPaginationModule,
    BsDatepickerModule.forRoot(),
  ],
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    P404Component,
    P500Component,
    LoginComponent,
    RegisterComponent,
    CustomersComponent,
    ProductsComponent,
    ProducerComponent,
    BannerComponent,
    TextareaComponent,
    InputComponent,
    DemoComponent,
    BillComponent,
    DetailComponent,
    TypeComponent,
    StatisticalComponent,
    ErrorComponent
  ],
  providers: [
    CookieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AppInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
