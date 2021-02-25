import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GalleryService {
  private url = 'http://localhost:8080/';
  constructor(
    private http :HttpClient
  ) { }
  getProducts() {
   
  
    return this.http.get(this.url+'product/allproducts');
  }
  filterProducts(categoryId:number){

  }
}
