
CREATE TABLE "users"(
    id       SERIAL PRIMARY KEY,
    name     Varchar (50) NOT NULL,
    username Varchar (50) NOT NULL,
    birthday Date NOT NULL,
    email    Varchar (50) NOT NULL,
    password Varchar (50) NOT NULL
);


INSERT INTO "users" (name, username, birthday, email, password) VALUES
('John Smith', 'johnsmith', '1990-01-01', 'john@example.com', 'johnjohn14'),
('Laura Martin', 'lauramart', '1992-02-02', 'laura@example.com', 'password123'),
('Raquel Mendoza', 'mendozaraquel', '1999-03-03', 'raquel@example.com', 'ares333'),
('Sarah Cameron', 'sarahcr', '1995-04-04', 'sarah@example.com', 'sarahcam200'),
('Chloe Decker', 'chloedecker', '1991-05-05', 'chloedecker@example.com', 'lucifer678'),
('Linda Johnson', 'lindajohnson', '1989-06-06', 'lindajohnson@example.com', 'passpass123'),
('Robert Miller', 'robertmiller', '1993-07-07', 'robertmiller@example.com', 'robertmllr125'),
('Patricia Garcia', 'patriciagarcia', '1994-08-08', 'patriciagarcia@example.com', 'patricia001'),
('David Anderson', 'davidanderson', '1990-09-09', 'davidanderson@example.com', 'davidis44'),
('Jennifer Martinez', 'jennifermartinez', '1987-10-10', 'jennifermartinez@example.com', 'jennmart85'),
('Sheldon Cooper', 'sheldoncooper', '1991-12-10', 'sheldon@example.com', 'sheldon741'),
('Penny Rolling', 'pennypenny', '1992-01-05', 'pennyrolling@example.com', 'smart7785'),
('Gloria Espino', 'gloriaspino', '1993-02-16', 'gloria@example.com', 'espinoza0078'),
('Alfred Steve', 'fredsteve', '1994-03-21', 'alsteve@example.com', 'alfred991'),
('Camila Mendes', 'camilastar', '1995-04-10', 'camimendes@example.com', 'riverdale3020'),
('Lili Reinhart', 'lilirht', '1996-05-07', 'lilireinhart@example.com', 'lilyrein772'),
('Veronica Lodge', 'veronica', '1997-11-18', 'lodgevr@example.com', 'veronica1032'),
('Archie Andrews', 'archiebald', '1998-06-05', 'archieandrews@example.com', 'bulldogs6603'),
('Ethel Mugs', 'ethelmugs', '1999-12-24', 'ethel@example.com', 'comicbooks056'),
('Dylan Sprouse', 'dylansprouse', '2000-10-19', 'dylan@example.com', 'sprouse5520');

