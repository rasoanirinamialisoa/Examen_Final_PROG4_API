SELECT
    type,
    date,
    amount,
    CASE
        WHEN type = 'Credit' THEN (SELECT SUM(amount) FROM transactions t2 WHERE t2.date <= t1.date)
        WHEN type = 'Debit' THEN (SELECT SUM(amount) FROM transactions t2 WHERE t2.date <= t1.date)
        END AS current_balance
FROM
    transactions t1
WHERE
    date BETWEEN '1999-01-12' AND '2024-03-23'
ORDER BY
    date DESC;
