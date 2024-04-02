CREATE TABLE "category_operation"(
    id   SERIAL PRIMARY KEY,
    name Varchar (50)
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

ALTER TABLE category_operation ADD description VARCHAR(100);
