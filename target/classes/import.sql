INSERT INTO Users (username, password, name, surname, email, role) VALUES ('AzLFox', '$2a$12$quvZsz0mVSM7.86S6Wz.U.ajk/S6iz7BgtlByxbmfaiQ7tBY6n0He','Данила','Лушков','azlfox0@gmail.com','Композитор');
INSERT INTO Users (username, password, name, surname, email, role) VALUES ('q', '$2a$12$quvZsz0mVSM7.86S6Wz.U.ajk/S6iz7BgtlByxbmfaiQ7tBY6n0He','Андрей','Малинин','malin@gmail.com','Читатель');

INSERT INTO Compositions (username, title, description, content, created_at) values ('1', 'Запутанный мир', 'Хорошая песня', 'Ну ты же хочешь остаться одной','2024-05-12');
INSERT INTO Compositions (username, title, description, content, created_at) values ('1', 'Хорошо в деревне летом', 'Произведение о том как хорошо в деревне летом', 'История одной деревни','2023-04-10');
INSERT INTO Compositions (username, title, description, content, created_at) values ('1', 'Однажды в лесу', 'История о трёх медведях', 'Однажды в лесу','2022-02-12');

INSERT INTO Comments(content, create_at, composition,username) values ('Ура новая песня !','2024-05-12','1','1');
INSERT INTO Comments(content, create_at, composition,username) values ('-_-','2024-05-12','2','1');

INSERT INTO Likes(username, title) values('1','1');