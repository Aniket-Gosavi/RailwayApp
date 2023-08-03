import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AdminserviceService } from '../adminservice.service';
import { Booking } from '../model/booking';
import { TrainDetails } from '../model/trainDetails';
import { Observable } from 'rxjs';
declare var Razorpay:any;
@Component({
  selector: 'app-bookreceipt',
  templateUrl: './bookreceipt.component.html',
  styleUrls: ['./bookreceipt.component.css']
})
export class BookreceiptComponent {

  num:any;
  bookingDetails: Booking; 
  id:any;
  train: TrainDetails;

  constructor(private user: UserService, private router: Router, private admin: AdminserviceService){}

  ngOnInit() {
    this.bookingDetails = this.user.currentBooking();
    this.train = new TrainDetails;

    this.num = this.admin.returnID();
    this.num = this.num * this.bookingDetails.numberOfTravellers;
    // console.log(this.admin.getById(this.admin.returnID()));
    // if(this.bookingDetails.trainNo === 9713){
    //   this.num = 400 * this.bookingDetails.numberOfTravellers;
    // }else if(this.bookingDetails.trainNo === 9710){
    //   this.num = 300 * this.bookingDetails.numberOfTravellers;
    // }else if(this.bookingDetails.trainNo === 9012){
    //   this.num = 180 * this.bookingDetails.numberOfTravellers;
    // }else if(this.bookingDetails.trainNo === 9172){
    //   this.num = 1200 * this.bookingDetails.numberOfTravellers;
    // }else if(this.bookingDetails.trainNo === 7897){
    //   this.num = 1200 * this.bookingDetails.numberOfTravellers;
    // }else{
    //   this.num = 350 * this.bookingDetails.numberOfTravellers;
    // }
  }

  printReceipt(){
    window.print();
  }

  makePayment() {
    this.createTransaction();
  }

  createTransaction(){
    var response = this.user.createTransaction(this.num).subscribe(
      (response) =>{
        console.log(response);
        this.openTransactionModel(response);
      },
      (error) =>{
        console.log(error);
      }
    )
  }

  openTransactionModel(response:any){
    var options = {
      order_id:response.orderId,
      key:response.key,
      amount:response.amount,
      currency:response.currency,
      name: 'Aniket',
      description: "Payment Train Booking",
      image: 'https://cdn.pixabay.com/photo/2023/06/18/04/57/crimson-collared-tanager-8071235_640.jpg',
      handler:(response :any)=>{
        this.processResponse(response);
      },
      prefill :{
        name:'Aniket',
        email:'aniketgosavi99@gmail.com',
        contact:'8380994363',
      },
      notes:{
        address:'Online train Booking'
      },
      theme:{
        color:"#F37254"
      }
    };

    var razorpay = new Razorpay(options);
    razorpay.open();
  }
  processResponse(resp:any){
    console.log(resp);
    this.router.navigate(['mybook'])
  }

}
