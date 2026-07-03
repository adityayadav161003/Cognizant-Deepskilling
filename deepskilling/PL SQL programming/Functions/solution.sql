-- Scenario 1: CalculateAge
CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE) RETURN NUMBER IS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

-- Scenario 2: CalculateMonthlyInstallment
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_amount NUMBER,
    p_rate NUMBER,
    p_duration_years NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_months NUMBER;
BEGIN
    v_monthly_rate := (p_rate / 100) / 12;
    v_months := p_duration_years * 12;
    IF v_monthly_rate = 0 THEN
        RETURN p_amount / v_months;
    ELSE
        RETURN p_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_months)) / (POWER(1 + v_monthly_rate, v_months) - 1);
    END IF;
END;
/

-- Scenario 3: HasSufficientBalance
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_acc NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_bal NUMBER;
BEGIN
    SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = p_acc;
    RETURN v_bal >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/
