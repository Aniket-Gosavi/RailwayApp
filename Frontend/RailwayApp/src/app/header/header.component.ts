import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from '../services/storage.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ConfirmationDialogComponentComponent } from '../confirmation-dialog-component/confirmation-dialog-component.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private roles: string[];
  isLoggedIn = false;
  AddTrains = false;
  searchTrains = false;
  showTrain = false;
  mybook = false;
  delete = false;
  book = false;
  cancel = false;
  username: string;
  searchTerm:string ='';
  typeData!:any;
  clickCart:boolean = false;

  constructor(private storageService:StorageService,
              private router:Router,private dialog: MatDialog){

  }
  ngOnInit(): void {
    this.isLoggedIn = !!this.storageService.getToken();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;

      this.AddTrains = this.roles.includes('ROLE_ADMIN');
      this.showTrain = this.roles.includes('ROLE_ADMIN');
      this.delete = this.roles.includes('ROLE_ADMIN');
      this.searchTrains = this.roles.includes('ROLE_USER');
      this.book = this.roles.includes('ROLE_USER');
      this.cancel = this.roles.includes('ROLE_USER');
      this.mybook = this.roles.includes('ROLE_USER');

      this.username = user.username;
    }
  }

  onLogout() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponentComponent);

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.storageService.signOut();
        this.router.navigate(['login']).then(() => {
          window.location.reload();
        });
      }
    });
  }

  onClickCart(){
    this.clickCart = true;
    this.router.navigate(['/cart']);
  }

  onClickElectronicShopping(){
    this.router.navigate(['/home']).then(()=>{
      window.location.reload();
    });
  }
}
