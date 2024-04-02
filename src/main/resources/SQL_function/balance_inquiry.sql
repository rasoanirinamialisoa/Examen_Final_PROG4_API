SELECT
    t.id_accounts AS account_id,
    COALESCE(SUM(CASE WHEN t.type = 'Deposit' THEN t.amount ELSE 0 END), 0) -
    COALESCE(SUM(CASE WHEN t.type = 'Withdraw' THEN t.amount ELSE 0 END), 0) AS principal_balance,
    COALESCE(SUM(CASE WHEN l.status = 'Active' THEN l.amount ELSE 0 END), 0) AS loan_amount,
    COALESCE(SUM(CASE WHEN l.status = 'Active' THEN l.amount * (l.interest_rate / 100) ELSE 0 END), 0) AS loan_interest
FROM
    transactions t
        LEFT JOIN
    loans l ON t.id_accounts = l.id_accounts
WHERE
        t.date <= current_date
GROUP BY
    t.id_accounts;
