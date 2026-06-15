import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div style="padding: 20px;">
      <h2>Courses List</h2>
      <ul>
        <li *ngFor="let course of courses">{{ course | uppercase }}</li>
      </ul>
    </div>
  `
})
export class AppComponent {
  courses: string[] = ['Angular', 'Spring Boot', 'Data Structures'];
}
