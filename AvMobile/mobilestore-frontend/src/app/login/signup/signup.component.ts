import { LoginService } from './../login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';

interface Gender {
  gender: string;
  viewValue: string;
}
interface Role {
  role: string;
  viewValue: string;
}
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {
  
  genders: Gender[] = [
    {gender: 'M', viewValue: 'Male'},
    {gender: 'F', viewValue: 'Female'},
    {gender: 'O', viewValue: 'Other'}
    ]; 
    roles: Role[] = [
      {role: 'CUSTOMER', viewValue: 'CUSTOMER'}
     
      ];
  firstName=''
  lastName=''
  gender=''
  phone=''
  role=''
  email=''
  password=''
  confirmPassword=''
  constructor(
    private service :LoginService,
    private toaster :ToastrService,
    private router : Router
  ) { }

  ngOnInit(): void {
    
  }
  onSignup() {
    if (this.firstName.length == 0) {
      this.toaster.warning('please enter first name')
    } else if (this.lastName.length == 0) {
      this.toaster.warning('please enter last name')
    } else if (this.email.length == 0) {
      this.toaster.warning('please enter email name')
    } else if (this.phone.length == 0) {
      this.toaster.warning('please enter phone name')
    } else if (this.password.length == 0) {
      this.toaster.warning('please enter password')
    } else if (this.confirmPassword.length == 0) {
      this.toaster.warning('please confirm password')
    } else if (this.password != this.confirmPassword) {
      this.toaster.warning('password does match')
    } else {
      this.service
        .signup(this.firstName,this.lastName,this.gender,this.phone,this.role, this.email, this.password,this.confirmPassword)
        .subscribe(response => {
          console.log(response)
          // if (response[this.role.CUSTOMER] == 'success') {
          //   this.toaster.success('succesfully signed up new account')
          //   this.router.navigate(['/login/signin'])
          // } else {
          //   this.toaster.error(response['error'])
          // }
          
          const body=response.toString
          if(isNaN(Number(body)))
          {
            this.router.navigate(['/signin'])
          }
          else
          {
            this.router.navigate(['/signup'])
          }
        })
    }
  }
  onCancel() {
    this.firstName='';
    this.lastName='';
    this.gender='';
    this.phone='';
    this.role='';
    this.email = '';
    this.password = '';
    this.confirmPassword='';
}
}
//insert into user(user_id,email,first_name,gender,last_name,password,phone,user_role) values (default,'amey@gmail.com','amey','m','deshpande','test@123','98653285','ADMIN');

//insert into user(user_id,email,first_name,gender,last_name,password,phone,user_role) values (default,'deshpande_amey@yahoo.com','om','m','pande','test@123','986565885','CUSTOMER');