import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private url = 'http://localhost:8080/admin/';
  constructor(
    private http: HttpClient
  ) { }
  getProducts() {
   
  
    return this.http.get(this.url+'proList');
  }
  addProduct(pro_name:string,pro_price:string,color:string,quantity:string,image:any,brandName:string,categoryName:string){
    const body=new FormData()
    console.log(image)
    body.append("proName",pro_name)
    body.append("proPrice", pro_price)
    body.append("color", color)
    
    body.append("proQuantity",  quantity)
    body.append("imageFile", image)
  
    body.append("brandName", brandName)
    body.append("categoryName", categoryName)

    
    return this.http.post(this.url+'addproduct',body)
  }
  
  public delete(id: number){
    return this.http.delete(this.url + "deletebyid/" + id);
    }
    editProduct(id:number,pro_name:string,pro_price:string,color:string,quantity:string,imageFile:any,brandName:string,categoryName:string){
      const body=new FormData()
  console.log(imageFile)
      body.append("proName",pro_name)
      body.append("proPrice", pro_price)
      body.append("color", color)
      
      body.append("proQuantity",  quantity)
      body.append("imageFile", imageFile)
    
      body.append("brandName", brandName)
      body.append("categoryName", categoryName)
  
      
      return this.http.put(this.url+'updateproduct'+'/'+id,body)
    }
}
