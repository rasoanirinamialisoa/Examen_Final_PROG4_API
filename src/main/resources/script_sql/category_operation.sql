CREATE TABLE "category_operation"(
    id   SERIAL PRIMARY KEY,
    name Varchar (50) NOT NULL
);



INSERT INTO "category_operation" (name) VALUES
('Groceries'),
('Utilities'),
('Rent'),
('Salary'),
('Entertainment'),
('Travel'),
('Education'),
('Healthcare'),
('Insurance'),
('Investments');