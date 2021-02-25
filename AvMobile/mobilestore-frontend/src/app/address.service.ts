import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private url = 'http://localhost:8080/'
  constructor(
    private http :HttpClient
  ) { }
  

  getAddresses() {
   
    return this.http.get(this.url)
  }
  addAddress( line1: string, line2: string, city: string, state: string, zipCode: string) {
   

    const body = {
      
      line1: line1,
      line2: line2,
      city: city,
      state: state,
      zipCode: zipCode
    }

    return this.http.post(this.url, body)
  }
}
