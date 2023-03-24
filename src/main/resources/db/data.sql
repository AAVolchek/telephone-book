DROP TABLE Contacts;
DROP SEQUENCE "SEQ_CONTACT";

CREATE SEQUENCE "SEQ_CONTACT";

CREATE TABLE Contacts(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    birthday DATE,
    social_profile VARCHAR(100)
);

INSERT INTO Contacts(id, name, last_name, birthday, social_profile)
VALUES (1000, 'Сергей', 'AGK', '1922-02-02', '')