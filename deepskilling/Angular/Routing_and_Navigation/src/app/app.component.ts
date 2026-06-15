import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  template: `
    <nav>
      <a routerLink="/home">Home</a> | 
      <a routerLink="/students">Students List</a>
    </nav>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {}
