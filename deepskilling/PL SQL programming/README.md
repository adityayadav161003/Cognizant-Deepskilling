# PL/SQL Programming Deepskilling

This module contains database scripts, schemas, and procedural SQL programs covering Oracle PL/SQL concepts including stored logic, triggers, exception handling, and packages.

## Base Schema
The core database setup is defined in [`schema.sql`](file:///c:/Users/adipi/OneDrive/Desktop/Cognizant_deepskilling/deepskilling/PL%20SQL%20programming/schema.sql) which sets up tables like `Customers`, `Accounts`, `Transactions`, `Loans`, and `Employees`.

## Topics Covered

1. **Control Structures** (`Control_Structures`)
   - Conditional statements (`IF-THEN-ELSE`) and iterative loops (`FOR`, `WHILE`) for applying interest, salary updates, and conditional logic.
2. **Cursors** (`Cursors`)
   - Implicit and explicit cursors for batch processing, including updating monthly transaction statements and loan interest processing.
3. **Error Handling** (`Error_Handling`)
   - Exception handling syntax using custom exceptions (`PRAGMA EXCEPTION_INIT`, user-defined exceptions) for transaction safety.
4. **Functions** (`Functions`)
   - Stored database functions to calculate metrics such as interest rates, age from birthdates, and loan balances.
5. **Stored Procedures** (`Stored_Procedures`)
   - Writing stored procedures for transaction execution (e.g., transfers, withdrawals) with output parameters and transactional controls.
6. **Triggers** (`Triggers`)
   - Designing row-level and statement-level triggers for auditing records, enforcing invariants (e.g., checking minimum balances), and updating logs.
7. **Packages** (`Packages`)
   - Creating PL/SQL packages with specifications and bodies to group related types, items, and subprograms (e.g., Customer Management utilities).
