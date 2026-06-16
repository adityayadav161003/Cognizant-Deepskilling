import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  standalone: true,
  template: `
    <div style="padding: 20px; font-family: sans-serif;">
      <h1>Angular Hands-On Lab 10</h1>
      <p>Student Course Portal milestone completed.</p>
    </div>
  `
})
export class AppComponent {
  title = 'student-course-portal';
}
