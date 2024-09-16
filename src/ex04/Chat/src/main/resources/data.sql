INSERT INTO users(login, password)
VALUES ('arboriob', 'arboriob'),
       ('hakekett', 'hakekett'),
       ('risahamm', 'risahamm'),
       ('starkill', 'starkill'),
       ('kattiede', 'kattiede');

INSERT INTO room(name, creator)
VALUES ('genom', (SELECT id FROM users WHERE login = 'arboriob')),
       ('progress', (SELECT id FROM users WHERE login = 'hakekett')),
       ('universe', (SELECT id FROM users WHERE login = 'risahamm')),
       ('evolution', (SELECT id FROM users WHERE login = 'starkill')),
       ('singularity', (SELECT id FROM users WHERE login = 'kattiede'));

INSERT INTO message(author, room, text)
VALUES ((SELECT id FROM users WHERE login = 'arboriob'),
        (SELECT id FROM room WHERE name = 'genom'),
        'pink colored room'),
       ((SELECT id FROM users WHERE login = 'hakekett'),
        (SELECT id FROM room WHERE name = 'progress'),
        'blue colored room'),
       ((SELECT id FROM users WHERE login = 'risahamm'),
        (SELECT id FROM room WHERE name = 'universe'),
        'aqua colored room'),
       ((SELECT id FROM users WHERE login = 'starkill'),
        (SELECT id FROM room WHERE name = 'evolution'),
        'violet colored room'),
       ((SELECT id FROM users WHERE login = 'kattiede'),
        (SELECT id FROM room WHERE name = 'singularity'),
        'orange colored room');