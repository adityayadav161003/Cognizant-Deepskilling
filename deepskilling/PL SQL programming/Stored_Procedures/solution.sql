-- Scenario 1: ProcessMonthlyInterest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts SET Balance = Balance * 1.01 WHERE AccountType = 'Savings';
    COMMIT;
END;
/

-- Scenario 2: UpdateEmployeeBonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_dept IN VARCHAR2,
    p_bonus_pct IN NUMBER
) IS
BEGIN
    UPDATE Employees SET Salary = Salary * (1 + p_bonus_pct/100) WHERE Department = p_dept;
    COMMIT;
END;
/

-- Scenario 3: TransferFunds
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_acc IN NUMBER,
    p_to_acc   IN NUMBER,
    p_amount   IN NUMBER
) IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_acc;
    IF v_balance >= p_amount THEN
        UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_acc;
        UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_acc;
        COMMIT;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Insufficient balance.');
    END IF;
END;
/
