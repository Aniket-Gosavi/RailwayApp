import { Component } from '@angular/core';
import { Booking } from '../model/booking';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AdminserviceService } from '../adminservice.service';

@Component({
  selector: 'app-showbookings',
  templateUrl: './showbookings.component.html',
  styleUrls: ['./showbookings.component.css']
})
export class ShowbookingsComponent {

  bk:Booking[];
  email:any;

  constructor(private user:UserService,
    private router:Router,
    private admin:AdminserviceService) { }

  ngOnInit(){
    this.user.getBooking().subscribe(data=>{
      this.bk=data;
      console.log(data);
    });
  }


  Cancel(id:any){
    this.user.get(id).subscribe(data=>{
      id=data;
      console.log(data);
    })
    alert("Your Booking has been cancelled Successfully, You'll receive a confirmation mail for it ");
    window.location.reload();
  }

}
