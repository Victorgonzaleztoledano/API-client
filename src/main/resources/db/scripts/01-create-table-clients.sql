CREATE TABLE clients (
    dni CHAR(9) PRIMARY KEY,
    name VARCHAR(20) not null,
    date DATE not null,
    country VARCHAR(20) not null,
    premium BOOLEAN not null
);