-- Users (passwords are bcrypt hashes of 'password123')
INSERT INTO Users (name, email, password_hash) VALUES
    ('Alice',   'alice@example.com', '$2y$10$1PayKCBD9oNXtKvYcslnHOcjom5JkJKdP.SYCxaS5SeUwI4qLYGaW'),
    ('Bob',     'bob@example.com',   '$2y$10$1PayKCBD9oNXtKvYcslnHOcjom5JkJKdP.SYCxaS5SeUwI4qLYGaW');

-- Global links
INSERT INTO Links (url, url_hash) VALUES
    ('https://developer.mozilla.org/en-US/docs/Web/JavaScript',
     'a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2'),
    ('https://docs.python.org/3',
     'b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3');

-- Cards for Alice (user_id = 1)
INSERT INTO Cards (user_id, title, description) VALUES
    (1, 'JavaScript Basics',  'Core JS concepts to review'),
    (1, 'Python Data Model',  'Dunder methods and protocols');

-- Cards for Bob (user_id = 2)
INSERT INTO Cards (user_id, title, description) VALUES
    (2, 'HTTP Fundamentals', 'Status codes, methods, headers');

-- CardLinks
INSERT INTO CardLinks (card_id, link_id, label, description, display_order) VALUES
    (1, 1, 'MDN JS Docs',    'Primary reference for JS basics', 0),
    (2, 2, 'Python 3 Docs',  'Official Python 3 documentation', 0);

-- CardTextBlocks
INSERT INTO CardTextBlocks (card_id, content) VALUES
    (1, 'var is function-scoped; let and const are block-scoped.'),
    (1, 'Arrow functions do not bind their own `this`.'),
    (2, '__repr__ should return an unambiguous string representation.'),
    (3, 'GET is idempotent and safe. POST is neither.'),
    (3, '401 Unauthorized means unauthenticated; 403 Forbidden means unauthorized.');

-- CardSchedule (day 1, 4, 7 offsets computed from today 2026-05-18)
INSERT INTO CardSchedule (card_id, scheduled_date) VALUES
    (1, '2026-05-19'),  -- day 1
    (1, '2026-05-22'),  -- day 4
    (1, '2026-05-25'),  -- day 7
    (2, '2026-05-19'),
    (2, '2026-05-22'),
    (3, '2026-05-19');
