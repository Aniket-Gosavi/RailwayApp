import { Component } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { Router } from '@angular/router';
import { TrainDetails } from '../model/trainDetails';

@Component({
  selector: 'app-addtrains',
  templateUrl: './addtrains.component.html',
  styleUrls: ['./addtrains.component.css']
})
export class AddtrainsComponent {

  td: any = new TrainDetails();

  constructor(private adminservice:AdminserviceService,private router:Router){}


  addTrains(){
    this.adminservice.addtrain(this.td).subscribe(data=>{
      console.log(data);
      alert("Train Details added successFully");
      this.router.navigate(['show'])
  }, (error) => {
    alert("Sorry but You Cannot Insert a duplicate Record");
  });
}
}
