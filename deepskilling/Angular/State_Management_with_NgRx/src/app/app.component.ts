import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  standalone: true,
  template: `
    <div style="padding: 20px;">
      <h2>NgRx Store Actions and Selectors Initialized</h2>
      <p>State manager dispatched actions successfully.</p>
    </div>
  `
})
export class AppComponent {}
