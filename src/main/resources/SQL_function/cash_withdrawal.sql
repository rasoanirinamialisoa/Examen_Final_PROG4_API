WITH RECURSIVE AccountCredit AS (
    SELECT
        a.id AS account_id,
        a.balance AS balance,
        (s.salary / 3) AS credit_limit
    FROM
        accounts a
            JOIN
        (SELECT id, SUM(amount) AS salary FROM transactions WHERE type = 'Salary' GROUP BY id) s
        ON
                a.id = s.id
)
SELECT
    account_id,
    balance,
    credit_limit,
    1.0 AS first_week_rate,
    2.0 AS subsequent_weeks_rate,
    CASE
        WHEN balance + credit_limit >= withdrawal_amount THEN 'Success: Retrait autorisé'
        ELSE 'Erreur: Solde insuffisant pour le retrait'
        END AS withdrawal_status
FROM
    AccountCredit ac
        JOIN
    (SELECT
         id_accounts,
         SUM(amount) AS withdrawal_amount
     FROM
         transactions
     WHERE
             type = 'Withdraw'
       AND date = current_date
     GROUP BY
         id_accounts) w
    ON
            ac.account_id = w.id_accounts;
