INSERT INTO usuario(username,email,password,enabled, data_cadastro)
VALUES ('Jairo Sousa','jaironsousa@gmail.com','$2a$10$p8HpLTDPVgXhhWihuT7CZOd1r8UeGZurR7XgT85OPFJ99o7WYnQqC', true,'2017-01-17');

INSERT INTO user_roles (usuario, perfil)
VALUES (001, 'ROLE_ADMIN');
INSERT INTO user_roles (usuario, perfil)
VALUES (001, 'ROLE_USER');
