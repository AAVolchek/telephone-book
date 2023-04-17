DROP TABLE Contacts;
DROP SEQUENCE "contact_sequence";

CREATE SEQUENCE "contact_sequence";

CREATE TABLE Contacts(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    phone_number VARCHAR(20),
    email VARCHAR(100),
    birthday DATE,
    social_profile VARCHAR(100)
);

INSERT INTO Contacts(id, name, last_name, phone_number, email, birthday, social_profile)
VALUES (1000, 'John', 'Doe', '+12124567890', 'SomeoneJohn@example1.com','1990-02-02', 'instagram/JohnDoe');

INSERT INTO Contacts(id, name, last_name, phone_number, email, birthday, social_profile)
VALUES (1001, 'Jane', 'Doe', '+12124569999', 'SomeoneJane@example2.com','1995-01-01', 'instagram/JaneDoe');