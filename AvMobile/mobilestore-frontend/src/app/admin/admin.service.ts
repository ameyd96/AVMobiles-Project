import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService implements CanActivate {

  private url='http://localhost:8080/admin/'
  constructor(
    private router :Router,
    private http :HttpClient
  ) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean  {
    if(!sessionStorage['token']){
      this.router.navigate(['/signin'])

      return false
    }else{
      return true
    }
  }
  getUsers(){
    return this.http.get(this.url+'userList')
  }
  public deleteUser(id: number){
    return this.http.delete(this.url  + id);
    }
}
