import { Component } from '@angular/core';
import { Booking } from '../model/booking';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AdminserviceService } from '../adminservice.service';
import Swal from'sweetalert2';

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


  Cancel(id: any) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'Do you want to cancel this booking?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, cancel it',
      cancelButtonText: 'No, keep it',
    }).then((result) => {
      if (result.isConfirmed) {
        this.user.get(id).subscribe((data) => {
          id = data;
          console.log(data);
        });
  
        Swal.fire('Cancelled!', 'Your booking has been cancelled successfully. You will receive a confirmation email for it.', 'success').then(() => {
          window.location.reload();
        });
      }
    });
  }
  

}
