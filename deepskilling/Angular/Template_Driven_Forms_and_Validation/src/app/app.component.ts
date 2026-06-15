import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule],
  template: `
    <form #studentForm="ngForm" (ngSubmit)="onSubmit(studentForm.value)">
      <input name="email" ngModel required email #emailRef="ngModel" placeholder="Email" />
      <div *ngIf="emailRef.invalid && emailRef.touched" style="color: red;">Email is required and must be valid</div>
      <button type="submit" [disabled]="studentForm.invalid">Submit</button>
    </form>
  `
})
export class AppComponent {
  onSubmit(val: any) { alert('Form Submitted: ' + JSON.stringify(val)); }
}
