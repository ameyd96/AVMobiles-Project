import { Router } from '@angular/router';
import { AddressComponent } from './../address/address.component';
import { ToastrService } from 'ngx-toastr';
import { ProductCartService } from './../product-cart.service';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-product-cart',
  templateUrl: './product-cart.component.html',
  styleUrls: ['./product-cart.component.css']
})
export class ProductCartComponent implements OnInit {

  sum=0
  item=[]
  totalPrice=0
  products=[]
  uid=0
  arr:any
  constructor(
    private cartService :ProductCartService,
    private toastr :ToastrService,
    private modalService: NgbModal,
    private router :Router
   
  ) { }

  ngOnInit(): void {
    this.uid=parseInt(sessionStorage.getItem("userId"))
    this.loadCartItems()
    
  }
  onPlaceOrder() {
    const modalRef = this.modalService.open(AddressComponent, {size: 'lg'})
    modalRef.result.finally(() => {
      this.loadCartItems()
    })
  }
  onLogout(){

    sessionStorage.clear()
    this.router.navigate(['/signin'])
  }
  onDelete(i){

  }


  loadCartItems() {
    this.cartService
      .getCartItems(this.uid)
      .subscribe(response => {
        console.log(response)
      
          this.totalPrice = 0
          this.arr = response
          console.log(this.arr)
         
          this.arr.forEach(p => {
            var q=parseInt(p.quantity)
            var pr=parseInt(p.product.proPrice)
            this.totalPrice += (q * pr)
            
          })
       
      })
  }

  updateQuantity(id) {
   
      // delete the item from cart
      this.cartService
        .deleteItemFromCart(id)
        .subscribe(response => {
         console.log(response)
         if(response==null){
          this.router.navigate(["/product-cart"])
         }
         else{
           this.toastr.error("Cart item not deleted")
         }
        })

        
    
  }
  onCheckout(){
    this.cartService
    .sendemail(this.uid,this.totalPrice)
    .subscribe(response => {
      console.log(response)
      if(response!=null){
        this.toastr.success("Thank You For Shopping, Your Order Has Been Placed...")
        this.router.navigate(['/product-gallery'])
      }
      
      
     })
  }
}
