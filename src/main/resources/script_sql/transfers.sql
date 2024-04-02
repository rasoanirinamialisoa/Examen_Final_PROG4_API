
CREATE TABLE "transfers"(
    id                        SERIAL PRIMARY KEY,
    amount                    decimal NOT NULL,
    same_bank                 Bool NOT NULL,
    other_bank                Bool NOT NULL,
    other_bank_name           Varchar (50) NOT NULL,
    other_account_number      VARCHAR (50),
    id_accounts               INT NOT NULL REFERENCES accounts(id)
);


<<<<<<< HEAD
INSERT INTO "transfers" (amount, same_bank, other_bank, other_bank_name, other_account_number, id_accounts) VALUES
( 100.00, false, true, 'BOA', '2345678901', 11),
( 150.00, true, false, '', '3456789012', 12),
( 200.00, false, true, 'BNI', '4567890123', 13),
( 250.00, false, true, 'BMOI', '5678901234', 14),
( 300.00, true, false, '', '6789012345', 15);
=======
INSERT INTO "transfers" (to_account_id, amount, same_bank, other_bank, other_bank_name, id_accounts) VALUES
('23456789', 100.00, false, true, 'BOA', 1),
('34567890', 150.00, true, false, '', 2),
('45678901', 200.00, false, true, 'BNI', 3),
('56789012', 250.00, false, true, 'BMOI', 4),
('67890123', 300.00, true, false, '', 5);

>>>>>>> 02f05f656d55ed0e20e7881ac3ae7f9e3fd3cfbd
