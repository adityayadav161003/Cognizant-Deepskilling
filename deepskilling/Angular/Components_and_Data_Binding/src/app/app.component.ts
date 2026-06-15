import { Component } from '@angular/core';
import { StudentComponent } from './student.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [StudentComponent],
  template: `<app-student></app-student>`
})
export class AppComponent {}
