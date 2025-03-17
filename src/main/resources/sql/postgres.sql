-- Active: 1741859232906@@127.0.0.1@5432@postgres
CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('ATTENDEE');
INSERT INTO roles (name) VALUES ('ORGANIZER');

SELECT * FROM roles;

ALTER TABLE user_roles
ALTER COLUMN user_id TYPE UUID USING user_id::uuid;

