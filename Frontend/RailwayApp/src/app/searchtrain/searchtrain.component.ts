import { Component, OnInit } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { TrainDetails } from '../model/trainDetails';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import  Swal  from 'sweetalert2'

@Component({
  selector: 'app-searchtrain',
  templateUrl: './searchtrain.component.html',
  styleUrls: ['./searchtrain.component.css']
})
export class SearchtrainComponent {

  td: TrainDetails[];

  sourceName:any;
  destinationName:any;
  date:any;

  
  constructor(private user:UserService,
    private router:Router,
    private admin:AdminserviceService) { }

  ngOnInit() {
    this.showData();
  }

  showData(){
    this.admin.get().subscribe(data=>{
      this.td=data;
      console.log(data);
    });
  }

  bookTrain(trainno:any,td:any){
    this.user.saveNo(trainno);
    this.admin.saveID(td);
    this.router.navigate(['book']);
  }

  searchData() {
    this.user.searchTrain(this.sourceName, this.destinationName, this.date).subscribe(
        data => {
            this.td = data;
            console.log(data);
        },
        (error) => {
            // Use SweetAlert for displaying an error message
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Sorry, No Train Available',
            }).then(() => {
                // Reload the window after user clicks OK
                window.location.reload();
            });
        }
    );
}
}
