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


--changset nikishin:task_tomany
CREATE TABLE PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIMEZONE DEFAULT now()
);

--changset nikishin:add_post_price_history
alter table auto_posts add column id_price_history int references PRICE_HISTORY(id);

--changset nikishin:add_participates
create table participates (
    id serial primary key,
    post_id int not null REFERENCES auto_posts(id),
    user_id int not null REFERENCES auto_users(id)
);
