import { Component } from '@angular/core';

@Component({
  selector: 'app-student',
  template: `
    <div style="padding: 15px; border: 1px solid #ccc;">
      <h2>Student Details: {{ studentName }}</h2>
      <button (click)="changeName()">Change Name</button>
    </div>
  `
})
export class StudentComponent {
  studentName: string = "Alice Smith";
  changeName() { this.studentName = "Bob Jones"; }
}
