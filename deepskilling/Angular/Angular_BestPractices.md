# Angular Development Best Practices Guide

Core guidelines, patterns, and design recommendations for building robust, maintainable, and high-performance Angular applications.

## 1. Component Architecture & Data Binding
Keep components clean and focused. Delegate complex logic to services. Use appropriate change detection strategies.
- **OnPush Change Detection**: For components that only depend on immutable `@Input()` properties, use `ChangeDetectionStrategy.OnPush` to minimize checks and optimize rendering performance.
- **Input/Output Binding**: Use standard `@Input()` and `@Output()` decorators for parent-child communication.

### Recommended Code Pattern
```typescript
import { Component, Input, Output, EventEmitter, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserCardComponent {
  @Input() user!: { id: number; name: String; email: String };
  @Output() selectUser = new EventEmitter<number>();

  onCardClick() {
    this.selectUser.emit(this.user.id);
  }
}
```

## 2. Reactive Forms vs Template-Driven Forms
- **Reactive Forms (Recommended for Complex Logic)**: Use Reactive Forms for robust validation, programmatic control, and unit testing capabilities.
- **Template-Driven Forms**: Good for simple forms with minimal validation rules.

### Recommended Code Pattern (Reactive Form Validation)
```typescript
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html'
})
export class UserRegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      console.log('Register Payload:', this.registerForm.value);
    }
  }
}
```

## 3. RxJS Memory Leak Prevention
Always clean up active subscriptions in components to prevent memory leaks.
- **Async Pipe (Preferred)**: Resolves observables directly in HTML templates and unsubscribes automatically when the component is destroyed.
- **takeUntil Operator**: Unsubscribes programmatic subscriptions cleanly during the `ngOnDestroy` lifecycle hook.

### Recommended Code Pattern (takeUntil)
```typescript
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { UserService } from './user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  users: any[] = [];

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getUsers()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => this.users = data);
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
```

## 4. NgRx State Management (Uni-Directional Data Flow)
For large-scale state representation, use NgRx to implement uni-directional data flow using Store, Actions, Reducers, and Selectors.

### Recommended Code Pattern
```typescript
// Actions
import { createAction, props } from '@ngrx/store';
export const loadUsers = createAction('[User List] Load Users');
export const loadUsersSuccess = createAction('[User List] Load Users Success', props<{ users: any[] }>());

// Reducer
import { createReducer, on } from '@ngrx/store';
export const initialState: { users: any[] } = { users: [] };
export const userReducer = createReducer(
  initialState,
  on(loadUsersSuccess, (state, { users }) => ({ ...state, users }))
);
```
