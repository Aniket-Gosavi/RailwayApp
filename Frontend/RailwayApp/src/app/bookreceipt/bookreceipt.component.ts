import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AdminserviceService } from '../adminservice.service';
import { Booking } from '../model/booking';
import { TrainDetails } from '../model/trainDetails';
import { Observable } from 'rxjs';
declare var Razorpay: any;

@Component({
  selector: 'app-bookreceipt',
  templateUrl: './bookreceipt.component.html',
  styleUrls: ['./bookreceipt.component.css'],
})
export class BookreceiptComponent {
  payment:any = "Pending";
  paymentDone: boolean = false;
  num: any;
  bookingDetails: Booking;
  id: any;
  train: TrainDetails;

  constructor(
    private user: UserService,
    private router: Router,
    private admin: AdminserviceService
  ) {}

  ngOnInit() {
    this.bookingDetails = this.user.currentBooking();
    this.train = new TrainDetails();

    this.num = this.admin.returnID();
    this.num = this.num * this.bookingDetails.numberOfTravellers;
  }

  printReceipt() {
    window.print();
  }

  makePayment() {
    this.createTransaction();
  }

  createTransaction() {
    var response = this.user.createTransaction(this.num).subscribe(
      (response) => {
        console.log(response);
        this.openTransactionModel(response);
        this.paymentDone = true
        this.payment = "Paid"
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openTransactionModel(response: any) {
    var options = {
      order_id: response.orderId,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      name: 'Aniket',
      description: 'Payment Train Booking',
      image:
        'https://cdn.pixabay.com/photo/2023/06/18/04/57/crimson-collared-tanager-8071235_640.jpg',
      handler: (response: any) => {
        this.processResponse(response);
      },
      prefill: {
        name: 'Aniket',
        email: 'aniketgosavi99@gmail.com',
        contact: '8380994363',
      },
      notes: {
        address: 'Online train Booking',
      },
      theme: {
        color: '#F37254',
      },
    };

    var razorpay = new Razorpay(options);
    razorpay.open();
  }

  processResponse(resp: any) {
    console.log(resp);
  }
}
