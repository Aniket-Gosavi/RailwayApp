import { Component } from '@angular/core';
import { Booking } from '../model/booking';
import { UserService } from '../user.service';
import { Route, Router } from '@angular/router';
import { TrainDetails } from '../model/trainDetails';
import { AdminserviceService } from '../adminservice.service';
import { MatDialog } from '@angular/material/dialog';
import { Passenger } from '../model/Passenger';

// declare var Razorpay:any;
@Component({
  selector: 'app-book-train',
  templateUrl: './book-train.component.html',
  styleUrls: ['./book-train.component.css']
})
export class BookTrainComponent {

  successmsg:any;
  enablePassengerForms:boolean;
  num:any;
  bk: any = new Booking();
  book:Booking;
  td:TrainDetails[];
  passenger:any = {firstName: '',lastName: '',Category: '',Gender: ''};
  
  constructor(private user:UserService,private router:Router,private admin: AdminserviceService,private dialog: MatDialog){}

  booking(){
    this.user.bookTrain(this.bk).subscribe(data=>{
      this.book=this.user.currentBooking();
      console.log(this.book);
      this.router.navigate(['receipt']);
  });
  }

  Add(){
    
    if (this.bk.numberOfTravellers > 0) {
      this.bk.passengers = [];

      // Create and store passenger details based on the number of travelers
      for (let i = 0; i < this.bk.numberOfTravellers - 1; i++) {
        this.bk.passengers.push(this.passenger);
      }
      this.enablePassengerForms = true;
    }
  }

  ngOnInit(){
    this.bk.trainNo = this.user.returnNo();
  }
  
}
