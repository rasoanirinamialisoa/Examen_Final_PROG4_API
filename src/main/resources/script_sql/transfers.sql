
CREATE TABLE "transfers"(
    id                        SERIAL PRIMARY KEY,
    to_account_id             INT NOT NULL,
    amount                    decimal NOT NULL,
    same_bank                 Bool NOT NULL,
    other_bank                Bool NOT NULL,
    other_bank_name           Varchar (50) NOT NULL,
    id_accounts               INT NOT NULL REFERENCES accounts(id)
);