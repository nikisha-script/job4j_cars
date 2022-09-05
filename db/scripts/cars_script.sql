--liquibase formatted sql

--changeset nikishin:Ucreate_auto_user
create table if not exists auto_users (
    id serial primary key,
    login text,
    password text
);

--changeset nikishin:Ucreate_auto_post
create table if not exists auto_posts (
    id serial primary key,
    text text,
    created timestamp,
    auto_user_id int references auto_users(id)
);

--changeset nikishin:insert_user
insert into auto_users(login,password) values('admin', 'admin');

--changeset nikishin:insert_user_task
INSERT INTO auto_users (login, password) VALUES ('Ivanov', 'root');
INSERT INTO auto_users (login, password) VALUES ('Petrov', 'root');
INSERT INTO auto_users (login, password) VALUES ('Sidorov', 'root');

