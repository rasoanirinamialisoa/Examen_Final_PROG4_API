CREATE TABLE "loans"(
    id            SERIAL PRIMARY KEY,
    amount        decimal NOT NULL,
    interest_rate decimal NOT NULL,
    start_date    Date NOT NULL,
    end_date      Date NOT NULL,
    status        Varchar (50) NOT NULL,
    creation_date Date NOT NULL,
    update_date   Date NOT NULL,
    id_accounts   INT NOT NULL REFERENCES accounts(id)
);


INSERT INTO "loans" (amount, interest_rate, start_date, end_date, status, creation_date, update_date, id_accounts) VALUES
(1000.00, 5.5, '2023-01-01', '2023-12-31', 'Active', '2023-01-01', '2023-01-01', '1'),
(1500.00, 6.0, '2023-02-01', '2023-12-31', 'Active', '2023-02-01', '2023-02-01', '2'),
(2000.00, 5.0, '2023-03-01', '2023-12-31', 'Active', '2023-03-01', '2023-03-01', '3'),
(2500.00, 5.5, '2023-04-01', '2023-12-31', 'Active', '2023-04-01', '2023-04-01', '4'),
(3000.00, 6.0, '2023-05-01', '2023-12-31', 'Active', '2023-05-01', '2023-05-01', '5');

