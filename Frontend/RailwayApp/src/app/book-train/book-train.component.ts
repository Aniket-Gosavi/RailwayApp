import { Component } from '@angular/core';
import { Booking } from '../model/booking';
import { UserService } from '../user.service';
import { Route, Router } from '@angular/router';
import { TrainDetails } from '../model/trainDetails';
import { AdminserviceService } from '../adminservice.service';
import { LoginErrorDialogComponent } from '../login-error-dialog/login-error-dialog.component';
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
  boo:Booking;
  td:TrainDetails[];
  passenger:any = {firstName: '',lastName: '',Category: '',Gender: ''};
  
  constructor(private user:UserService,private router:Router,private admin: AdminserviceService,private dialog: MatDialog){}

  book(){
    this.user.bookTrain(this.bk).subscribe(data=>{
      this.boo=this.user.currentBooking();
      console.log(this.boo);
      this.router.navigate(['receipt']);
  });
  }

  Add(){
    
    if (this.bk.numberOfTravellers > 1) {
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
  
  msg(){
    this.successmsg = 'Booking Done For '+this.bk.firstName+
    ' Please Procide to Pay the amount '+this.num;

    // Open the dialog with the error message
    this.dialog.open(LoginErrorDialogComponent, {
      data: this.successmsg,
    });
  }
}
