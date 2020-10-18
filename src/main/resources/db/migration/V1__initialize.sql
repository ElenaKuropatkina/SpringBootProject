DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id serial, title VARCHAR(255), price int, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('IPhone4', 200), ('IPhone4s', 250), ('IPhone5', 300), ('IPhone5s', 350), ('IPhone5SE', 400), ('IPhone6', 450), ('IPhone7', 500), ('IPhone8', 500), ('IPhoneX', 800), ('IPhoneXR', 700), ('IPhoneXS', 800), ('IPhone11', 750), ('IPhone8SE', 600), ('IPad', 400), ('IPad2', 500), ('IPadMini', 350), ('MacAir13', 800), ('MacAir15', 1000), ('Airpods', 100), ('Airpods2', 120);

DROP TABLE users IF EXISTS;
CREATE TABLE users(id bigserial, login VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(80) NOT NULL, first_name VARCHAR(50), last_name VARCHAR(50), PRIMARY KEY (id));

DROP TABLE roles IF EXISTS;
CREATE TABLE roles(id serial, name VARCHAR(50) NOT NULL, PRIMARY KEY (id));

DROP TABLE user_roles IF EXISTS;
CREATE TABLE user_roles(user_id INT NOT NULL, role_id INT NOT NULL, PRIMARY KEY (user_id, role_id), FOREIGN KEY (user_id) REFERENCES users(id), FOREIGN KEY (role_id) REFERENCES roles(id));

INSERT INTO users (login, password, first_name, last_name) VALUES('admin@mail.ru','$2y$12$hVffZrvXYAvNHeJongBg1OElBeFrq9up6rJA/8RXwV4VVgFEeChH6','Admin','Admin'), ('manager@mail.ru','$2y$12$Pl5mfjz.SgD82Ef8F5GVB.16k2bHmHMUakjfplxxrzmPxqYOrUNcO','Bob','Boo'), ('simpleUser1@mail.ru','$2y$12$vqrd2wRle9AOsfRh05Jeg.kTCxmVj3Y6JCkcvTCsncdY5wNkYSP3y','Vasya','Pupkin'), ('simpleUser2@mail.ru','$2y$12$gUAg77EUmvgujb6Wnq7I7.d0HcMy/hWqiv5ySFIQVPXwxz5e0qeyi','Petr','Ivanov');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_CUSTOMER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1), (2, 2), (3, 3), (4, 3);