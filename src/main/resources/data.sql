INSERT INTO app_user (id, email, password, user_role, username)
VALUES (
        1001,
        'jan@gmail.com',
        '$2a$10$NwX3PVPGH3om4c.7yZDWbeQCSBla6jnHis6YhJEDiLIfF84D5yoxi',
        'USER',
        'jan'
       ),
       (
           1002,
           'emile@gmail.com',
           '$2a$10$UP4ICRtMGepIBtBczyO4ouW7eK/yk59e71GnWHveOiD7b7KX.3D9G',
           'ADMIN',
           'emile'
       );


INSERT INTO shop (id, active, email, name, token_hash)
VALUES (
           1001,
           true,
           'hema@gmail.nl',
           'Hema',
           '$2a$10$TKQN/7VDHG2rQLGJjt9Mz.24xHTB47oVqBdUj8ZNfxanpvxEmA2Rq'
       );

-- INSERT INTO tab (claimed, app_user_id, created_at, id, shop_id, file_name)
-- VALUES (
--         false,
--         1001,
--         '2026-06-09 10:05:54.933733',
--         1001,
--         1001,
--         '2a1cb779-2455-4f02-b418-57e969be1c8a.pdf'
--        );