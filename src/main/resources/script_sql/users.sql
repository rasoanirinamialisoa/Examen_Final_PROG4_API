
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       birth_date DATE NOT NULL CHECK (birth_date <= CURRENT_DATE - INTERVAL '21 years'),
    monthly_salary FLOAT NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users (first_name, last_name, birth_date, monthly_salary, email, password)
VALUES ('John', 'Doe', '1990-03-15', 5000.00, 'john.doe@example.com', 'password123');
