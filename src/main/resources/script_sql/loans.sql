CREATE TABLE "loans"(
    id            SERIAL PRIMARY KEY,
    amount        decimal ,
    interest_rate decimal ,
    start_date    Date ,
    end_date      Date ,
    status        Varchar (50) ,
    creation_date Date ,
    update_date   Date ,
    id_accounts   INT NOT NULL REFERENCES accounts(id)
);


INSERT INTO "loans" (amount, interest_rate, start_date, end_date, status, creation_date, update_date, id_accounts) VALUES
(1000.00, 5.5, '2023-01-01', '2023-12-31', 'Active', '2023-01-01', '2023-01-01', '1'),
(1500.00, 6.0, '2023-02-01', '2023-12-31', 'Active', '2023-02-01', '2023-02-01', '2'),
(2000.00, 5.0, '2023-03-01', '2023-12-31', 'Active', '2023-03-01', '2023-03-01', '3'),
(2500.00, 5.5, '2023-04-01', '2023-12-31', 'Active', '2023-04-01', '2023-04-01', '4'),
(3000.00, 6.0, '2023-05-01', '2023-12-31', 'Active', '2023-05-01', '2023-05-01', '5');

