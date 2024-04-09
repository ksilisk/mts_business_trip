create table if not exists users
(
    user_id  SERIAL PRIMARY KEY,
    username text NOT NULL UNIQUE,
    password text NOT NULL
)