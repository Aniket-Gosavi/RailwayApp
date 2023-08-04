import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog-component',
  template: `
    <h2 mat-dialog-title>Confirm Logout</h2>
    <mat-dialog-content>Are you sure you want to log out?</mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button mat-dialog-close>No</button>
      <button mat-raised-button color="warn" [mat-dialog-close]="true">Yes</button>
    </mat-dialog-actions>
  `,
  styleUrls: ['./confirmation-dialog-component.component.css']
})
export class ConfirmationDialogComponentComponent {
  constructor(public dialogRef: MatDialogRef<ConfirmationDialogComponentComponent>){}
}
