CREATE FUNCTION get_category_summary(start_date DATE, end_date DATE)
    RETURNS TABLE (
        registration_date DATE,
        total_income NUMERIC,
        total_expense NUMERIC
        ) AS $$
BEGIN
RETURN QUERY
SELECT
    DATE(t.registration_date) AS registration_date,
    COALESCE(SUM(CASE WHEN co.type = 'INCOME' THEN t.amount ELSE 0 END), 0) AS total_income,
    COALESCE(SUM(CASE WHEN co.type = 'EXPENSE' THEN t.amount ELSE 0 END), 0) AS total_expense
FROM
    transactions t
    JOIN
    category_operation co ON t.id_category_operation = co.id
WHERE
    t.registration_date BETWEEN start_date AND end_date
GROUP BY
    DATE(t.registration_date)
ORDER BY
    DATE(t.registration_date);
END;
$$ LANGUAGE plpgsql;
