--liquibase formatted sql

--changeset nikishin:create_auto_users
create table if not exists auto_users (
    id serial primary key,
    login text unique,
    password text
);


comment on table auto_users is 'Пользователи';
comment on column auto_users.id is 'Идентификатор пользователя';
comment on column auto_users.login is 'Логин пользователя';
comment on column auto_users.password is 'Пароль пользователя';