import { AddressComponent } from './address/address.component';
import { HeaderComponent } from './header/header.component';
import { ProductCartComponent } from './product-cart/product-cart.component';
import { ProductGalleryComponent } from './product-gallery/product-gallery.component';
import { FooterComponent } from './footer/footer.component';
import { DashboardComponent } from './user/dashboard/dashboard.component';
import { UserListComponent } from './user-list/user-list.component';
import { ProductEditComponent } from './product/product-edit/product-edit.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { SignupComponent } from './login/signup/signup.component';
import { AdminService } from './admin/admin.service';
import { HomeComponent } from './admin/home/home.component';
import { SigninComponent } from './login/signin/signin.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductAddComponent } from './product/product-add/product-add.component';


const routes: Routes = [
  {path:'',redirectTo:'/signin',pathMatch:'full'},
  {path: 'signin',component:SigninComponent},
  {path: 'signup',component:SignupComponent},
  //{path: 'home',component:HomeComponent,canActivate: [AdminService]}
  {path: 'home',component:HomeComponent},
  {path: 'product-list',component:ProductListComponent},
  {path: 'product-add',component:ProductAddComponent},
  {path: 'product-edit',component:ProductEditComponent},
  {path: 'user-list',component:UserListComponent},
  {path: 'dashboard',component:DashboardComponent},
  {path: 'footer',component:FooterComponent},
  {path: 'product-gallery',component:ProductGalleryComponent},
  {path: 'product-cart',component:ProductCartComponent},
  {path: 'header',component:HeaderComponent},
  {path: 'address',component:AddressComponent}
 
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
