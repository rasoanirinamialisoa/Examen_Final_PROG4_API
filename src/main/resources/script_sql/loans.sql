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
