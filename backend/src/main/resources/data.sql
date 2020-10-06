INSERT IGNORE INTO testdb.roles (id, role_name) VALUES (1, 'ROLE_USER');
INSERT IGNORE INTO testdb.roles (id, role_name) VALUES (2, 'ROLE_ADMIN');

INSERT IGNORE INTO testdb.users (id, email, name, password, user_name) VALUES (1, 'emre.calik01@gmail.com', 'Emre Çalık', '$2a$10$o3W0Ts4nRSbajXZ36HjGoeoEdO7y9g5W9OuUm4s5/mkcHURkAEHgi', 'emreboun');
INSERT IGNORE INTO testdb.users (id, email, name, password, user_name) VALUES (2, 'kaan.can@tei.com', 'Kaan Can', '$2a$10$kmP5RmLJf0V595mEtHov9OI1G0bYItoVlV5MsJayGrNmZTV5zW/O2', 'kaancan');
INSERT IGNORE INTO testdb.users (id, email, name, password, user_name) VALUES (3, 'yusuf.gokyer@ge.com', 'Yusuf Gökyer', '$2a$10$EVxu5ZZC4PMyiSEOkzWSWe0HWGx/gueUz4IOM8tkIKr0hD1pZcfEm', 'yusufge');

INSERT IGNORE INTO testdb.todos (id, created_at, updated_at, created_by, updated_by, description, expired_at, user_id) VALUES (1, '2020-08-15 00:02:48', '2020-08-15 00:02:48', 1, 1, 'Learn Spring Boot', '2020-08-15 10:02:48', 1);
INSERT IGNORE INTO testdb.todos (id, created_at, updated_at, created_by, updated_by, description, expired_at, user_id) VALUES (2, '2020-08-15 00:02:49', '2020-08-15 00:02:49', 1, 1, 'Run 6km twice a week', '2021-02-13 15:22:48', 1);
INSERT IGNORE INTO testdb.todos (id, created_at, updated_at, created_by, updated_by, description, expired_at, user_id) VALUES (3, '2020-08-15 00:02:49', '2020-08-15 00:02:49', 1, 1, 'Finish MSc Degree', '2020-11-14 07:42:48', 2);
INSERT IGNORE INTO testdb.todos (id, created_at, updated_at, created_by, updated_by, description, expired_at, user_id) VALUES (4, '2020-08-15 00:02:49', '2020-08-15 00:02:49', 1, 1, 'Quit smoking', '2020-08-22 00:02:48', 3);

INSERT IGNORE INTO testdb.users_roles (user_id, role_id) VALUES (1, 1);
INSERT IGNORE INTO testdb.users_roles (user_id, role_id) VALUES (2, 1);
INSERT IGNORE INTO testdb.users_roles (user_id, role_id) VALUES (3, 1);
INSERT IGNORE INTO testdb.users_roles (user_id, role_id) VALUES (1, 2);