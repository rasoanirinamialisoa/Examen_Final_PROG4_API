CREATE TABLE "accounts"(
    id SERIAL PRIMARY KEY,
    account_number Varchar (50) NOT NULL,
    balance float NOT NULL,
    id_users INT NOT NULL REFERENCES users(id)
);



INSERT INTO "accounts" (account_number, balance, id_users) VALUES
('1234567890', 100000.00, '1'),
('2345678901', 10500.50, '2'),
('3456789012', 552000.75, '3'),
('4567890123', 2500000.00, '4'),
('5678901234', 3050000.25, '5'),
('6789012345', 35000.00, '6'),
('7890123456', 400000.50, '7'),
('8901234567', 545000.75, '8'),
('9012345678', 500000.00, '9'),
('0123456789', 551000.25, '10');