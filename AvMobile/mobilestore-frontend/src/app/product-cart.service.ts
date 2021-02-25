import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductCartService {
  private url = 'http://localhost:8080/user/'
  constructor(
    private http :HttpClient
  ) { }
  addItemToCart(uid:number,productId: number, quantity: number) {


    return this.http.get(this.url+'addToCart/'+uid+'/'+productId+ '/'+quantity)
  }
  getCartItems(id:number)
  {
    return this.http.get(this.url+'cartitems/'+id)
  }
  getProductById(id:number)
  {
    return this.http.get(this.url+'getProductById/'+id)
  }
  deleteItemFromCart(id: number) {
    
    return this.http.delete(this.url + "delcart"+ "/" + id)
  }
  sendemail(uid:number,totalPrice:number){
   
    
    return this.http.get(this.url + "placeorder/"+uid+"/"+totalPrice)
  }
}
