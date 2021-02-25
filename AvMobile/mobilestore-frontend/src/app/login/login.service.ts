import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService{

  private url ='http://localhost:8080'
  constructor(
    private router :Router,
    private http: HttpClient
  ) { }
  
  login(email:string,password:string){
    const body={
      email : email,
      password : password

    } 
    return this.http.post(this.url +"/user/login" , body)
  }
  signup(firstName:string,lastName:string,gender:string,phone:string,role:string,email:string,password:string,confirmPassword:string){
    const body={
      firstName:firstName,
      lastName:lastName,
      gender:gender,
      phone:phone,
      user_role:role,
      email:email,
      password:password,
      confirmPassword:confirmPassword
    }

    return this.http.post(this.url + "/user/register",body)

  }
  
}


// amey@gmail.com               | amey       | m      | NULL  | deshpande | test@123 | 98653285  | ADMIN  