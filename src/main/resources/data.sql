INSERT INTO app_user (id, email, password, role, username)
VALUES (1, 'jan@gmail.com', '$2a$10$IV9p9IDOg6vhzA7nM.p9CetfAzuRSYgRNaeOHyMYlirkfEsBC5z1C',
        'ADMIN', 'jan');

INSERT INTO shop (active, id, email, name, token)
VALUES (true, 1, 'hema@gmail.nl',
        'Hema', '8f6ce52def162dff47941e8f5895bad8df5cb61186c3f5a1716d0591f9c5d3fb');