import { Component } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { TrainDetails } from '../model/trainDetails';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

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

  deleteTrain(id: any){
    this.admin.delete(id).subscribe(data=>{
      id=data;
      console.log(data);
    })
    alert("Deleted!!");
    window.location.reload();
    
  }

}
