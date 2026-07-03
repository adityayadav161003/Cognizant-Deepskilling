-- Scenario 1: CustomerManagement Package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddNewCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_bal NUMBER);
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_bal NUMBER);
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddNewCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_bal NUMBER) IS
    BEGIN
        INSERT INTO Customers VALUES (p_id, p_name, p_dob, p_bal, SYSDATE);
    END;
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_bal NUMBER) IS
    BEGIN
        UPDATE Customers SET Name = p_name, Balance = p_bal, LastModified = SYSDATE WHERE CustomerID = p_id;
    END;
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER IS
        v_bal NUMBER;
    BEGIN
        SELECT Balance INTO v_bal FROM Customers WHERE CustomerID = p_id;
        RETURN v_bal;
    END;
END CustomerManagement;
/
