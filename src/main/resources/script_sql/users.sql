
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       birth_date DATE CHECK (birth_date <= CURRENT_DATE - INTERVAL '21 years'),
    monthly_salary FLOAT,
    email VARCHAR(50),
    password VARCHAR(50)
);

ALTER TABLE users
    ADD COLUMN id_accounts INT;

ALTER TABLE users
    ADD CONSTRAINT fk_accounts
        FOREIGN KEY (id_accounts)
            REFERENCES accounts(id);

UPDATE users
SET id_accounts = 1
WHERE id = 1;

ALTER TABLE users
    ALTER COLUMN id_accounts DROP NOT NULL;
ALTER TABLE users
    ALTER COLUMN password DROP NOT NULL;



INSERT INTO users (first_name, last_name, birth_date, monthly_salary, email, password)
VALUES ('John', 'Doe', '1990-03-15', 5000.00, 'john.doe@example.com', 'password123', 1);
