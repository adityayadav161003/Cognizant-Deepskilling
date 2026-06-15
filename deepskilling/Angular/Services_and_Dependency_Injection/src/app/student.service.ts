import { Injectable } from '@angular/core';

@Injectable()
export class StudentService {
  getStudents() { return ['Alice', 'Bob', 'Charlie']; }
}
