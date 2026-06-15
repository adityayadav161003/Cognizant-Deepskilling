import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentService } from './student.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  providers: [StudentService],
  template: `
    <ul><li *ngFor="let s of students">{{ s }}</li></ul>
  `
})
export class AppComponent implements OnInit {
  students: string[] = [];
  constructor(private studentService: StudentService) {}
  ngOnInit() { this.students = this.studentService.getStudents(); }
}
