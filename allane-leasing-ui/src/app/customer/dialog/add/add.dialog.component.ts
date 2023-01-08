import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { CustomerService } from 'build/openapi/services';
import { Customer } from 'build/openapi/models';

@Component({
  selector: 'app-add.dialog',
  templateUrl: './add.dialog.html',
  styleUrls: ['./add.dialog.css']
})

export class AddDialogComponent {
  constructor(public dialogRef: MatDialogRef<AddDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Customer,
    public customerService: CustomerService) { }

  formControl = new FormControl('', [
    Validators.required
  ]);

  submitted = false;

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' : '';
  }

  submit() {
    // empty stuff
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  public confirmAdd(): void {
    this.submitted = true;
    let customer: Customer = { firstName: this.data.firstName, lastName: this.data.lastName, birthDate: this.data.birthDate };
    this.customerService.createCustomer({ body: customer })
      .pipe(
       // ToDo: catchError,
      )
      .subscribe((customer) => {
        this.dialogRef.close(200);
      });
  }
}
