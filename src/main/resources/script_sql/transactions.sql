CREATE TABLE "transactions"(
    id                    SERIAL PRIMARY KEY,
    type                  Varchar (50) NOT NULL,
    date                  Date NOT NULL,
    amount                decimal NOT NULL,
    id_accounts           INT NOT NULL REFERENCES accounts(id),
    id_category_operation INT NOT NULL REFERENCES category_operation(id)
);


INSERT INTO "transactions" (type, date, amount, id_accounts, id_category_operation) VALUES
('Deposit', '2023-01-01', 200.00, '1', '4'),
('Withdraw', '2023-02-15', 50.00, '1', '1'),
('Deposit', '2023-03-10', 300.00, '1', '4'),
('Withdraw', '2023-04-05', 100.00, '1', '5'),
('Deposit', '2023-05-20', 150.00, '1', '4'),
('Deposit', '2023-01-05', 300.00, '2', '4'),
('Withdraw', '2023-02-20', 100.00, '2', '2'),
('Deposit', '2023-03-15', 400.00, '2', '4'),
('Withdraw', '2023-04-10', 200.00, '2', '5'),
('Deposit', '2023-05-25', 250.00, '2', '4');