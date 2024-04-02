CREATE TABLE "category_operation"(
    id   SERIAL PRIMARY KEY,
    name Varchar (50),
    description varchar ( 100),
    type varchar (255)
);

INSERT INTO "category_operation" (name) VALUES
('Utilities'),
('Rent'),
('Salary'),
('Entertainment'),
('Travel'),
('Education'),
('Healthcare'),
('Insurance'),
('Investments');

