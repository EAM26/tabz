INSERT INTO app_user (id, email, password, user_role, username)
VALUES (1, 'jan@gmail.com', '$2a$10$IV9p9IDOg6vhzA7nM.p9CetfAzuRSYgRNaeOHyMYlirkfEsBC5z1C',
        'ADMIN', 'jan');

INSERT INTO shop (active, id, email, name, token)
VALUES (true, 1, 'hema@gmail.nl',
        'Hema', '8f6ce52def162dff47941e8f5895bad8df5cb61186c3f5a1716d0591f9c5d3fb');

INSERT INTO tab (claimed, app_user_id, created_at, id, shop_id, file_name)
VALUES (true, 1, '2026-06-09 10:05:54.933733',
        1, 1, '2a1cb779-2455-4f02-b418-57e969be1c8a.pdf');