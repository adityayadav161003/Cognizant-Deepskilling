# PL/SQL Programming Best Practices Guide

Core guidelines and design recommendations for writing robust, performant, and maintainable PL/SQL database scripts.

## 1. Exception Handling Architecture
Never use blank exception catchers like `WHEN OTHERS THEN NULL;` as they swallow critical operational bugs.

### Recommended Code Pattern
```sql
DECLARE
    e_insufficient_funds EXCEPTION;
    PRAGMA EXCEPTION_INIT(e_insufficient_funds, -20001);
    v_balance NUMBER;
BEGIN
    SELECT balance INTO v_balance FROM Accounts WHERE account_id = 101;
    IF v_balance < 500 THEN
        RAISE e_insufficient_funds;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Account record not found.');
    WHEN e_insufficient_funds THEN
        DBMS_OUTPUT.PUT_LINE('Error: Account balance drops below limit.');
        RAISE; -- re-raise exception to the caller
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected Database Error: ' || SQLERRM);
        RAISE;
END;
/
```

## 2. Cursor Management (Prevent Memory Leaks)
Always use Cursor FOR loops when possible, since Oracle automatically manages opening, fetching, and closing resources. If using explicit cursors, ensure resources close inside exception blocks.

### Recommended Code Pattern (Cursor FOR Loop)
```sql
-- Explicit declarations are managed automatically inside FOR loops
DECLARE
    CURSOR c_employees IS 
        SELECT employee_id, first_name, salary FROM Employees;
BEGIN
    FOR r_emp IN c_employees LOOP
        DBMS_OUTPUT.PUT_LINE(r_emp.first_name || ' earns: ' || r_emp.salary);
    END LOOP; -- cursor closes automatically here
END;
/
```

## 3. Transaction Safety (Atomicity)
Always structure procedural logic so that DML actions are atomic. Keep transaction bounds clear, and do not execute commits inside helper methods or triggers.

### Recommended Code Pattern
```sql
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_acc IN INT,
    p_to_acc   IN INT,
    p_amount   IN NUMBER
) IS
BEGIN
    UPDATE Accounts SET balance = balance - p_amount WHERE account_id = p_from_acc;
    UPDATE Accounts SET balance = balance + p_amount WHERE account_id = p_to_acc;
    -- Commit should be managed by the calling routine to allow multi-step transactions
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK; -- roll back all intermediate updates on error
        RAISE;
END;
/
```

## 4. Trigger Best Practices
- **Restrict Logic**: Keep triggers lightweight. Triggers should not contain complex calculations or business algorithms. Move that code into Stored Procedures.
- **Mutating Table Errors**: Avoid querying the table on which the trigger is defined to prevent mutating exceptions.
