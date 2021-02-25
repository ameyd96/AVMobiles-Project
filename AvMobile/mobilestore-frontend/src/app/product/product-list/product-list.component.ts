import { ProductEditComponent } from './../product-edit/product-edit.component';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProductService } from './../product.service';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductAddComponent } from '../product-add/product-add.component';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products:any
  constructor(private modalService: NgbModal,
    private toastr:ToastrService,
    private service :ProductService,
    private router :Router
    
  ) { }

  ngOnInit(): void {
    this.loadProducts()
  }
  loadProducts(){
    const observable=this.service.getProducts()
    observable.subscribe(response =>{
      console.log(response)
      
      this.products=response
    })
  }
  onEdit(product){
    const modalref= this.modalService.open(ProductEditComponent)

    const component= modalref.componentInstance as ProductEditComponent
    component.id=product.pid
    component.proName=product.proName
    component.proPrice=product.proPrice
    component.color=product.color
    component.proQuantity=product.proQuantity
    component.brandName=product.brandName
    component.categoryName=product.categoryName

    modalref.result.finally( ()=>{
      this.loadProducts()
    })
  }
  onAdd() {
    const modalRef = this.modalService.open(ProductAddComponent, {size: 'lg'})
    modalRef.result.finally(() => {
      this.loadProducts()
    })
  }
  onDelete(product){
    const result = confirm('Are you sure you want to remove this item?');
    console.log(product.pid);
    if (result) {
      this.service
        .delete(product.pid)
        .subscribe(response => {
         console.log(response.toString)
         
            this.loadProducts()
          
            
          //refershing is not happened
        })
    }
  }
}
