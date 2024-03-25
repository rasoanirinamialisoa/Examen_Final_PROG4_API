
CREATE TABLE "transfers"(
    id                        SERIAL PRIMARY KEY,
    to_account_id             INT NOT NULL,
    amount                    decimal NOT NULL,
    same_bank                 Bool NOT NULL,
    other_bank                Bool NOT NULL,
    other_bank_name           Varchar (50) NOT NULL,
    id_accounts               INT NOT NULL REFERENCES accounts(id)
);


INSERT INTO "transfers" (to_account_id, amount, same_bank, other_bank, other_bank_name, id_accounts) VALUES
('23456789', 100.00, false, true, 'BOA', 1),
('34567890', 150.00, true, false, '', 2),
('45678901', 200.00, false, true, 'BNI', 3),
('56789012', 250.00, false, true, 'BMOI', 4),
('67890123', 300.00, true, false, '', 5);
