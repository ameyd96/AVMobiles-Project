import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from './../product.service';
import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Product } from '../product';


@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {



  proName='';
  proPrice='';
  color='';
  proQuantity=''
  imageFile=undefined;
  brandName=''
  categoryName=''

 
  constructor(
    private productService:ProductService,
    private toastr :ToastrService,
    private modal: NgbActiveModal,
    private router :Router,
   
    
    
  ) { }

  ngOnInit(): void {
  
  }
  onAdd() {
    if (this.proName.length == 0) {
      this.toastr.warning('please enter title')
    } else if (this.proPrice.length == 0) {
      this.toastr.warning('please enter price')
    } else if (this.color.length == 0) {
      this.toastr.warning('please enter color')
    } else if (this.imageFile == undefined) {
      this.toastr.warning('please select an image')
    } else {
      this.productService
        .addProduct(this.proName, this.proPrice, this.color,this.proQuantity,this.imageFile, this.brandName, this.categoryName)
        .subscribe(response => {
          console.log(response)
          
         this.router.navigate(['/product-list'])
         this.modal.dismiss('ok')
         //refreshing is not happened
        })
    }
  }

  onImageSelected(event){
    console.log(event)
    console.log(event.target.files[0])
    this.imageFile=event.target.files[0]
  }
  onCancel() {
    this.modal.dismiss('cancel')
    this.router.navigate(['/product-list']);
  }
 


  
 
}
