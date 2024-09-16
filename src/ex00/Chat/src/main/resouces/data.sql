INSERT INTO users(login, password)
VALUES ('arboriob', 'arboriob'),
       ('hakekett', 'hakekett'),
       ('risahamm', 'risahamm'),
       ('starkill', 'starkill'),
       ('kattiede', 'kattiede');

INSERT INTO chatroom(name, owner_user_id)
VALUES ('genom', (SELECT id FROM users WHERE login = 'arboriob')),
       ('progress', (SELECT id FROM users WHERE login = 'hakekett')),
       ('universe', (SELECT id FROM users WHERE login = 'risahamm')),
       ('evolution', (SELECT id FROM users WHERE login = 'starkill')),
       ('singularity', (SELECT id FROM users WHERE login = 'kattiede'));

INSERT INTO message(user_id, chatroom_id, text)
VALUES ((SELECT id FROM users WHERE login = 'arboriob'),
        (SELECT id FROM chatroom WHERE name = 'genom'),
        'pink room'),
       ((SELECT id FROM users WHERE login = 'hakekett'),
        (SELECT id FROM chatroom WHERE name = 'progress'),
        'blue room'),
       ((SELECT id FROM users WHERE login = 'risahamm'),
        (SELECT id FROM chatroom WHERE name = 'universe'),
        'aqua room'),
       ((SELECT id FROM users WHERE login = 'starkill'),
        (SELECT id FROM chatroom WHERE name = 'evolution'),
        'violet room'),
       ((SELECT id FROM users WHERE login = 'kattiede'),
        (SELECT id FROM chatroom WHERE name = 'singularity'),
        'orange room');