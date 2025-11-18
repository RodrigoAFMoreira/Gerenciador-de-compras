INSERT INTO users (email, first_name, last_name, password, role)
VALUES (
    'admin@email.com',
    'Admin',
    'User',
    '$2a$10$n9qo8uC95M6YpRjkg9TjZ.e4XIb4M7k3k8a6uY3K7p8z9v0w1x2y3',
    'ADMIN'
);

INSERT INTO users (email, first_name, last_name, password, role)
VALUES (
    'user@email.com',
    'User',
    'Normal',
    '$2a$10$n9qo8uC95M6YpRjkg9TjZ.e4XIb4M7k3k8a6uY3K7p8z9v0w1x2y3',
    'USER'
);