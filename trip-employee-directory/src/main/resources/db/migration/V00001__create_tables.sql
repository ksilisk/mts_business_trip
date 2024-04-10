CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY,
    role TEXT
);

CREATE TABLE IF NOT EXISTS employees
(
    id              SERIAL PRIMARY KEY,
    username        TEXT UNIQUE,
    name            TEXT,
    surname         TEXT,
    patronymic      TEXT,
    phone           VARCHAR(17),
    email           TEXT,
    passport_seria  VARCHAR(4) UNIQUE,
    passport_number VARCHAR(6) UNIQUE,
    position        TEXT,
    master_id       INTEGER,
    role_id         INTEGER,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (master_id) REFERENCES employees (id)
);