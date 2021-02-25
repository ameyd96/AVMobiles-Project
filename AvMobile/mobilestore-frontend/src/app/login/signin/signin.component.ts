import { LoginService } from './../login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  email=''
  password=''
  constructor(
    private router : Router,
    private toastr : ToastrService,
    private service : LoginService) { }

  ngOnInit(): void {
  }

  onSignin(){
    if(this.email.length==0){
      this.toastr.warning('Enter your email')
    }else if(this.password.length==0){
      this.toastr.warning('Enter your password')
    }
    else{
      const observable = this.service.login(this.email,this.password)
      observable.subscribe(response =>{
        console.log(response)
        // if(response['status']=='success'){
          
          this.toastr.success('welcome')

          //const user=JSON.parse(response)
        const user=response
        
          console.log(user['userRole'])
          if(user['userRole']=='ADMIN'){
            sessionStorage['user_name']=user['firstName'] +' '+user['lastName']

            var body=JSON.stringify(user['id'])

            sessionStorage.setItem("userId",body);
            //sessionStorage['token']=user['token']
              //TODO check whether is admin or user
              //if admin
             
            this.router.navigate(['/home'])
          }
         else if(user['userRole']=='CUSTOMER'){
            sessionStorage['user_name']=user['firstName'] +' '+user['lastName']
            //sessionStorage['token']=user['token']
              //TODO check whether is admin or user
              //if admin
              var body=JSON.stringify(user['id'])

              console.log(body)
            sessionStorage.setItem("userId",body);
            this.router.navigate(['/dashboard'])
          }
          
          //TODO : else use
       // }
        else{
         this.toastr.error(response['error'])
       }
      })
  
    }
   
  }
  onCancel() {
    this.email = '';
    this.password = '';
}

onSignup(){
  this.router.navigate(['/signup']);
        
  }
}
