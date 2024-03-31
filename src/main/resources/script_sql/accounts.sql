CREATE TABLE accounts (
                          id SERIAL PRIMARY KEY,
                          account_number VARCHAR(50) UNIQUE,
                          balance FLOAT,
                          user_id INT REFERENCES users(id),
                          type_id INT REFERENCES account_types(id),
                          allows_overdraft BOOLEAN DEFAULT false,
                          overdraft_credit_percentage FLOAT DEFAULT 0.333, -- 1/3 par défaut
                          interest_rate_7_days FLOAT DEFAULT 0.01, -- 1% par défaut
                          interest_rate_after_7_days FLOAT DEFAULT 0.02 -- 2% par défaut
);

INSERT INTO accounts (account_number, balance, user_id, type_id, allows_overdraft, overdraft_credit_percentage, interest_rate_7_days, interest_rate_after_7_days)
VALUES
    ('1234567890', 1500.00, 1, 1, true, 0.333, 0.01, 0.02),
    ('2345678901', 25000.00, 1, 2, false, 0.333, 0.01, 0.02),
    ('3456789012', 50000.00, 1, 3, true, 0.333, 0.01, 0.02);

