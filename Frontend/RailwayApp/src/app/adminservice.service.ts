import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TrainDetails } from './model/trainDetails';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AdminserviceService {

  td:TrainDetails;
  id:any;

  constructor(private http: HttpClient) { }

  saveID(ID:any){ 
    this.id=ID;
  }

  savetrain(td:TrainDetails){
    this.td = td;
  }

  returntrain(){
    return this.td;
  }
  returnID(){
    return this.id;
  }

  get(){
    return this.http.get<TrainDetails[]>("http://localhost:8001/show");
  }

  addtrain(td:TrainDetails){
    return this.http.post<TrainDetails>("http://localhost:8001/add",td);
  }

  delete(id:any){
    return this.http.delete("http://localhost:8001/delete/"+id);
  }

  getById(id:any){
    return this.http.get<TrainDetails>("http://localhost:8001/findbyid/"+id);
  }

  updateTrain(id:any, train:TrainDetails){
    return this.http.put<TrainDetails>("http://localhost:8001/update/"+id,train);
  }
}
