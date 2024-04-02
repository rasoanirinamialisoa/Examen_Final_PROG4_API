CREATE TABLE account_types (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(100)
);

INSERT INTO account_types (name) VALUES
                                                       ('Checking Account'),
                                                       ('Savings Account'),
                                                       ('Investment Account');
