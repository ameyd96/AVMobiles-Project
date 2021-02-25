import { AddressService } from './../address.service';
import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  line1 = ''
  line2 = ''
  city = ''
  state = ''
  
  zipCode = ''
  constructor(
    private modal: NgbActiveModal,
    private toastr: ToastrService,
    private service :AddressService
  ) { }

  ngOnInit(): void {
  }
  onAdd() {
    //console.log(`title: ${this.title}`)
    if (this.line1.length == 0) {
      this.toastr.warning('please enter line 1')
    } else if (this.line2.length == 0) {
      this.toastr.warning('please enter line 2')
    } else if (this.city.length == 0) {
      this.toastr.warning('please enter city')
    } else if (this.state.length == 0) {
      this.toastr.warning('please enter state')
    } else if (this.zipCode.length == 0) {
      this.toastr.warning('please enter zip code')
    } else {
      this.service
        .addAddress( this.line1, this.line2,
            this.city, this.state, this.zipCode)
        .subscribe(response => {
          
        })
    }
  }
  onCancel() {
    this.modal.dismiss('cancel')
  }
}
