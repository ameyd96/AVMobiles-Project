import { LoginService } from './../../login/login.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(
    private router :Router,
    private login :LoginService
  ) { }

  ngOnInit(): void {
  }
  onLogout(){

    sessionStorage.clear()
    this.router.navigate(['/signin'])
  }
}
