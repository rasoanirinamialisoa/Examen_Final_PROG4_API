
CREATE TABLE "users"(
    id       SERIAL PRIMARY KEY,
    name     Varchar (50) NOT NULL,
    username Varchar (50) NOT NULL,
    birthday Date NOT NULL,
    email    Varchar (50) NOT NULL,
    password Varchar (50) NOT NULL
);