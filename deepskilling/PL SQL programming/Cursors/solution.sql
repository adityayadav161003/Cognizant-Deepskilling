-- Scenario 1: GenerateMonthlyStatements
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT t.TransactionID, t.AccountID, t.Amount, t.TransactionDate, c.Name
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE t.TransactionDate BETWEEN TRUNC(SYSDATE, 'MM') AND LAST_DAY(SYSDATE);
BEGIN
    FOR r IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE('Customer: ' || r.Name || ' | Acc: ' || r.AccountID || ' | Trans: ' || r.TransactionID || ' | Amt: ' || r.Amount);
    END LOOP;
END;
/

-- Scenario 2: ApplyAnnualFee Cursor
DECLARE
    CURSOR ApplyAnnualFee IS SELECT AccountID, Balance FROM Accounts FOR UPDATE;
    v_fee CONSTANT NUMBER := 20;
BEGIN
    FOR r IN ApplyAnnualFee LOOP
        UPDATE Accounts SET Balance = Balance - v_fee WHERE CURRENT OF ApplyAnnualFee;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: UpdateLoanInterestRates Cursor
DECLARE
    CURSOR UpdateLoanInterestRates IS SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
BEGIN
    FOR r IN UpdateLoanInterestRates LOOP
        UPDATE Loans SET InterestRate = InterestRate + 0.5 WHERE CURRENT OF UpdateLoanInterestRates;
    END LOOP;
    COMMIT;
END;
/
