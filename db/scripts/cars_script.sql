--liquibase formatted sql

--changeset nikishin:create_auto_user
create table if not exists auto_users (
    id serial primary key,
    login text unique,
    password text
);

--changeset nikishin:create_auto_post
create table if not exists auto_posts (
    id serial primary key,
    text text,
    created timestamp,
    auto_user_id int references auto_users(id)
);