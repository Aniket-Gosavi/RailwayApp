import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TrainDetails } from './model/trainDetails';
import { Booking } from './model/booking';
import { TransactionDetails } from './model/details';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  num:any;

  book:Booking;

  currentBooking(){
    return this.book;
  }

  findBooking(id:any){
    return this.http.get("http://localhost:8002/findbyId/"+id);
  }

  public createTransaction(amount:any){
    return this.http.get<TransactionDetails>("http://localhost:8002/createTransaction/"+amount);
  }

  saveNo(no:any){
    this.num=no;
  }

  returnNo(){
    return this.num;
  }

  get(id:any){
    return this.http.delete("http://localhost:8002/cancelbooking/"+id);
  }

  getTrain(tno:any){
    return this.http.get("http://localhost:8002/findbyno/"+tno);
  }

  searchTrain(source:any,destination:any,date:any){
    return this.http.get<TrainDetails[]>("http://localhost:8002/findbysourceanddestination/"+source+"/"+destination+"/"+date);
  }

  bookTrain(bk:Booking){
    this.book = bk;
    return this.http.post<Booking>("http://localhost:8002/book",bk);
  }

  getEmail(){
    return this.http.get("http://localhost:9098/api/auth/email");
  }

  getBooking(){
    return this.http.get<Booking[]>("http://localhost:8002/findbyemail");
  }
}
