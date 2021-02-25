import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { SigninComponent } from './login/signin/signin.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import { SignupComponent } from './login/signup/signup.component';
import { HomeComponent } from './admin/home/home.component';
import { ProductAddComponent } from './product/product-add/product-add.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductEditComponent } from './product/product-edit/product-edit.component';
import { UserListComponent } from './user-list/user-list.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';
import { FooterComponent } from './footer/footer.component';

import { ProductGalleryComponent } from './product-gallery/product-gallery.component';
import { ProductCartComponent } from './product-cart/product-cart.component';
import { HeaderComponent } from './header/header.component';
import { AddressComponent } from './address/address.component';
import { ThankyouComponent } from './thankyou/thankyou.component';



@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    SignupComponent,
    HomeComponent,
    ProductAddComponent,
    ProductListComponent,
    ProductEditComponent,
    UserListComponent,
    DashboardComponent,
    FooterComponent,
  
    ProductGalleryComponent,
    ProductCartComponent,
    HeaderComponent,
    AddressComponent,
    ThankyouComponent,
   
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
