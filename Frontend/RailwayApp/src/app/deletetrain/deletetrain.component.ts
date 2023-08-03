import { Component } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deletetrain',
  templateUrl: './deletetrain.component.html',
  styleUrls: ['./deletetrain.component.css']
})
export class DeletetrainComponent {

  deleteid:any;
  constructor(private admin:AdminserviceService,private router:Router){}

  deleteTrain(){
    this.admin.delete(this.deleteid).subscribe(data=>{
      this.deleteid=data;
      console.log(data);
    })
    alert("Deleted!!");
    this.router.navigate(['show'])
  }
}
