CREATE OR REPLACE FUNCTION get_income_and_expense_summary(start_date DATE, end_date DATE)
RETURNS TABLE (
    registration_date DATE,
    total_income NUMERIC,
    total_expense NUMERIC
) AS $$
BEGIN
RETURN QUERY
SELECT
    DATE(t.registration_date) AS registration_date,
    COALESCE(SUM(CASE WHEN UPPER(t.type) = 'DEPOSIT' THEN t.amount ELSE 0 END), 0) AS total_income,
    COALESCE(SUM(CASE WHEN UPPER(t.type) = 'WITHDRAW' OR UPPER(t.type) = 'WITHDRAWAL' THEN t.amount ELSE 0 END), 0) AS total_expense
FROM
    transactions t
WHERE
    t.registration_date BETWEEN start_date AND end_date
GROUP BY
    DATE(t.registration_date)
ORDER BY
    DATE(t.registration_date);
END;
$$ LANGUAGE plpgsql;
