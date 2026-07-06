INSERT INTO app_user (id, email, password, user_role, username)
VALUES (1001,
        'jan@gmail.com',
        '$2a$10$NwX3PVPGH3om4c.7yZDWbeQCSBla6jnHis6YhJEDiLIfF84D5yoxi',
        'USER',
        'jan'),
       (1002,
        'emile@gmail.com',
        '$2a$10$UP4ICRtMGepIBtBczyO4ouW7eK/yk59e71GnWHveOiD7b7KX.3D9G',
        'ADMIN',
        'emile');


INSERT INTO shop (id, active, email, name, token_hash)
VALUES (1001,
        true,
        'hema@gmail.nl',
        'Hema',
        '$2a$10$TKQN/7VDHG2rQLGJjt9Mz.24xHTB47oVqBdUj8ZNfxanpvxEmA2Rq');

INSERT INTO tab (claimed, app_user_id, created_at, id, shop_id, file_name, total_amount)
VALUES (true,
        1001,
        '2026-07-02 15:28:45.414183',
        1001,
        1001,
        'cc85a5eb-330e-40ad-b932-edc1ebcb16eb.pdf',
        100),
       (true,
        1001,
        '2026-07-02 15:29:50.24428',
        1002,
        1001,
        '2ce76bcd-e172-480b-8e38-380c632584f5.pdf',
        200),
       (true,
        1001,
        '2026-07-02 15:30:30.787395',
        1003,
        1001,
        'b3816815-197e-45a3-935c-217d5fde5064.pdf',
        300)