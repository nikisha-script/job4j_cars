create table if not exists auto_users (
    id serial primary key,
    login text,
    password text
);