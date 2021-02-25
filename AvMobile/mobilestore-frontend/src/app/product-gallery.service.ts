import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductGalleryService {
  private url='http://localhost:8080/'
  constructor(private http:HttpClient) { }
  filterProducts(category :string) {


    return this.http.post(this.url + "/filter",category)
  }
}
}
