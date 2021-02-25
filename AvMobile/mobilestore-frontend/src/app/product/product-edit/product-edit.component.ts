import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  id=0
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

  onEdit(){
    if(this.proName.length==0){
      this.toastr.warning('Please enter proName')
    }else if(this.proPrice.length==0){
      this.toastr.warning('please enter proPrice')
    }
    else if(this.color.length==0){
      this.toastr.warning('please enter color')
    }
    else if(this.proQuantity.length==0){
      this.toastr.warning('please enter proQuantity')
    }
    else if(this.brandName.length==0){
      this.toastr.warning('please enter brandName')
    } else if(this.categoryName.length==0){
      this.toastr.warning('please enter categoryName')
    }
    else if (this.imageFile == undefined) {
      this.toastr.warning('please select an image')
    }
    else{
      this.productService.
      editProduct(this.id,this.proName,this.proPrice,this.color,this.proQuantity,this.brandName,this.categoryName,this.imageFile).
      subscribe(response =>{
        this.modal.dismiss('ok')
      })
    }
  }
  onImageSelected(event){
    console.log(event)
    this.imageFile=event.target.files[0]
  }
  onCancel() {
    this.modal.dismiss('cancel')
    this.router.navigate(['/product-list']);
  }
}
