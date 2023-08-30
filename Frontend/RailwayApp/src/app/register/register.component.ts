import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { AuthService } from '../services/auth.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  user: any = {
    username: '',
    email: '',
    roles: ['user'],
    password: '',
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  constructor(
    private signupService: AuthService,
    private router: Router,
    private authService: AuthService
  ) {}
  
  ngOnInit(): void {

  }

  onCreateAccount() {
    this.authService.registerUser(this.user).subscribe({
      next: (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.goToLoginPage();
      },
      error: (error) => {
        this.isSignUpFailed = true;
        let errorMessage = "An error occurred while registering.";
  
        if (error && error.error && error.error.message) {
          if (error.error.message.includes("Username is already taken")) {
            errorMessage = "Username is already taken.";
          } else if (error.error.message.includes("Email is already in use")) {
            errorMessage = "Email is already in use.";
          }
        }
  
        Swal.fire({
          icon: 'error',
          title: 'Registration Error',
          text: errorMessage,
          confirmButtonText: 'OK'
        });
      }
    });
  }

  goToLoginPage() {
    this.router.navigate(['login']);
  }
}
