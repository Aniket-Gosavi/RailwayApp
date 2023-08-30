import { Component } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { TrainDetails } from '../model/trainDetails';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-show-trains',
  templateUrl: './show-trains.component.html',
  styleUrls: ['./show-trains.component.css']
})
export class ShowTrainsComponent {
  
  td:TrainDetails[];

  constructor(private admin:AdminserviceService,private router:Router){}

  ngOnInit(){
    this.showData();
  }

  showData(){
    this.admin.get().subscribe(data=>{
      this.td=data;
      console.log(data);
    });
  }

  updateUser(id: any) {
    this.admin.saveID(id);
    this.router.navigate(['update']);
  }

  deleteTrain(deleteid:any) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'Do you want to delete this train?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it',
      cancelButtonText: 'Cancel',
    }).then((result) => {
      if (result.isConfirmed) {
        this.admin.delete(deleteid).subscribe((data) => {
          deleteid = data;
          console.log(data);
        });
        Swal.fire('Deleted!', 'The train has been deleted.', 'success').then(() => {
          this.router.navigate(['show']);
        });
      }
    });
  }

}
