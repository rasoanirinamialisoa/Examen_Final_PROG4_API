CREATE TABLE "transactions"(
    id                    SERIAL PRIMARY KEY,
    type                  Varchar (50) NOT NULL,
    date                  Date NOT NULL,
    amount                decimal NOT NULL,
    id_accounts           INT NOT NULL REFERENCES accounts(id),
    id_category_operation INT NOT NULL REFERENCES category_operation(id)
);