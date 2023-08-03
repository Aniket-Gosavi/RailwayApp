import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { StorageService } from '../services/storage.service';
import { LoginErrorDialogComponent } from '../login-error-dialog/login-error-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userlogin = {
    username: '',
    password: ''
  }
  custId: any;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService,
    private router: Router,
    private storageService: StorageService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    if (this.storageService.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
      this.custId = this.storageService.getUser().username;
    }
  }

  onLogin() {
    this.authService.login(this.userlogin).subscribe(data => {
      console.log(data);
      this.storageService.saveToken(data.accessToken);
      this.storageService.saveUser(data);
      console.log(data);

      this.isLoginFailed = false;
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
      alert('You have successfully logged in as ' + this.roles);
      this.router.navigate(['']).then(() => {
        window.location.reload();
      });
    }, (error) => {
      console.log(error);
      this.isLoginFailed = true;
      this.errorMessage = 'Invalid username or password.';

      // Open the dialog with the error message
      this.dialog.open(LoginErrorDialogComponent, {
        data: this.errorMessage,
      });

      // Reset the userlogin object to clear the textboxes
      this.userlogin = {
        username: '',
        password: ''
      };
    });
  }
}
