import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  template: `
    <form [formGroup]="studentForm" (ngSubmit)="onSubmit()">
      <input formControlName="name" placeholder="Student Name" />
      <div *ngIf="studentForm.get('name')?.invalid && studentForm.get('name')?.touched" style="color: red;">Name is required</div>
      <button type="submit" [disabled]="studentForm.invalid">Enroll</button>
    </form>
  `
})
export class AppComponent {
  studentForm: FormGroup;
  constructor(private fb: FormBuilder) {
    this.studentForm = this.fb.group({
      name: ['', Validators.required]
    });
  }
  onSubmit() { alert('Enrolled Student: ' + this.studentForm.value.name); }
}
