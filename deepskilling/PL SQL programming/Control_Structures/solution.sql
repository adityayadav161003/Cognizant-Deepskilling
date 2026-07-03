-- Scenario 1: Apply 1% interest rate discount to loans for customers over 60
DECLARE
    CURSOR c_cust IS SELECT CustomerID, DOB FROM Customers;
    v_age NUMBER;
BEGIN
    FOR r IN c_cust LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, r.DOB) / 12);
        IF v_age > 60 THEN
            UPDATE Loans SET InterestRate = InterestRate - 1 WHERE CustomerID = r.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote VIP status for balance over 10000
ALTER TABLE Customers ADD IsVIP VARCHAR2(5) DEFAULT 'FALSE';
DECLARE
    CURSOR c_cust IS SELECT CustomerID, Balance FROM Customers;
BEGIN
    FOR r IN c_cust LOOP
        IF r.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'TRUE' WHERE CustomerID = r.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Due loan reminders
DECLARE
    CURSOR c_loans IS 
        SELECT l.LoanID, c.Name, l.EndDate FROM Loans l 
        JOIN Customers c ON l.CustomerID = c.CustomerID 
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR r IN c_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || r.Name || ' has loan ' || r.LoanID || ' due on ' || TO_CHAR(r.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/
