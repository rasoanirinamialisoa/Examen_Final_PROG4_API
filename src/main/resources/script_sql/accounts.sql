CREATE TABLE "accounts"(
    id SERIAL PRIMARY KEY,
    account_number Varchar (50) NOT NULL,
    balance float NOT NULL,
    id_users INT NOT NULL REFERENCES users(id)
);