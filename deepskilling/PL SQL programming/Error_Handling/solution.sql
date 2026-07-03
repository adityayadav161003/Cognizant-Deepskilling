-- Scenario 1: SafeTransferFunds Stored Procedure
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_acc IN NUMBER,
    p_to_acc   IN NUMBER,
    p_amount   IN NUMBER
) IS
    v_balance NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_acc FOR UPDATE;
    IF v_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;
    
    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_acc;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_acc;
    COMMIT;
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in account ' || p_from_acc);
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Account not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('SQL Error: ' || SQLERRM);
END;
/

-- Scenario 2: UpdateSalary Stored Procedure
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_emp_id IN NUMBER,
    p_pct    IN NUMBER
) IS
BEGIN
    UPDATE Employees SET Salary = Salary * (1 + p_pct/100) WHERE EmployeeID = p_emp_id;
    IF SQL%ROWCOUNT = 0 THEN
        RAISE NO_DATA_FOUND;
    END IF;
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_emp_id || ' does not exist.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('SQL Error: ' || SQLERRM);
END;
/

-- Scenario 3: AddNewCustomer Stored Procedure
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id    IN NUMBER,
    p_name  IN VARCHAR2,
    p_dob   IN DATE,
    p_bal   IN NUMBER
) IS
    duplicate_customer EXCEPTION;
    PRAGMA EXCEPTION_INIT(duplicate_customer, -1); -- Unique constraint violation
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_bal, SYSDATE);
    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX OR duplicate_customer THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_id || ' already exists.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('SQL Error: ' || SQLERRM);
END;
/
