import { Component } from '@angular/core';
import { TrainDetails } from '../model/trainDetails';
import { AdminserviceService } from '../adminservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-updatetrain',
  templateUrl: './updatetrain.component.html',
  styleUrls: ['./updatetrain.component.css']
})
export class UpdatetrainComponent {

  train:TrainDetails;
  id:any;

  constructor(private admin:AdminserviceService,private router:Router){}

  ngOnInit() {
    this.train = new TrainDetails;
    this.id = this.admin.returnID();
    this.admin.getById(this.id).subscribe(data=>{
      this.train = data;
    })
  }

  updateTrain(){
   this.admin.updateTrain(this.id,this.train).subscribe(data=>{
    console.log(data);
   });
   this.router.navigate(['show'])
  }
  onSubmit() {
    this.updateTrain();
  }
}
