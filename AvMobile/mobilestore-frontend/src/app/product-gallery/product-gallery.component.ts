import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ProductCartService } from './../product-cart.service';
import { GalleryService } from './../gallery.service';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-gallery',
  templateUrl: './product-gallery.component.html',
  styleUrls: ['./product-gallery.component.css']
})
export class ProductGalleryComponent implements OnInit {
  products:any
  categories = []
  category=''
   uid=0
  constructor(
    private router :Router,
    private galleryService :GalleryService,
    private cartService :ProductCartService,
    private toastr :ToastrService
  ) { }

  ngOnInit(): void {
    this.loadProducts()
    
    this.uid=parseInt(sessionStorage.getItem("userId"))
  }
  loadProducts(){
    const observable=this.galleryService.getProducts()
    observable.subscribe(response =>{
      console.log(response)
      
      this.products=response
    })
  }
  // filterProducts() {
  //   this.galleryService
  //     .filterProducts(this.category)
  //     .subscribe(response => {
  //       console.log(response)
  //       if (response['status'] == 'success') {
  //         console.log(this.category)
  //         this.products = response['data']
  //       } else {
  //         this.toastr.error(response['error'])
  //       }
  //     })
  // }

  onAddToCart(product) {
    console.log(this.uid)
    this.cartService
      .addItemToCart(this.uid,product.pid, 1)
      .subscribe(response => {
       console.log(response)
       if(response)
       {
         this.toastr.success("Product is added to cart....!!!")
       }
       else
       {
        this.toastr.warning("Failed add to cart....!!!")
       }
      })
  }
  onLogout(){

    sessionStorage.clear()
    this.router.navigate(['/signin'])
  }
}
//let uid=sessionStorage.getItem("userId");