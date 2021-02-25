import { Router } from '@angular/router';
import { AdminService } from './../admin/admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  user:any;
  constructor(
    private service : AdminService,
    private router :Router
  ) { }

  ngOnInit(): void {
    this.loadUsers()
  }

  loadUsers(){
    this.service.getUsers()
    .subscribe(response =>{
      console.log(response)
      this.user=response
      
    })
  }
  onDelete(u){
    const result = confirm('Are you sure you want to remove this item?');
    console.log(u.id);
    if (result) {
      this.service
        .deleteUser(u.id)
        .subscribe(response => {
         //console.log(response.toString)
         
            this.loadUsers()
          
            
          //refershing is not happened
        })
    }
  }
}
