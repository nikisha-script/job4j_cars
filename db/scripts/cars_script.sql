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

--changset nikishin:add_drivers
create table drivers(
    id serial primary key,
    name text
);

--changset nikishin:add_engine
create table if not exists engines(
    id serial primary key,
    name varchar(255) not null,
	power int not null
);

--changset nikishin:add_cars
create table cars(
    id serial primary key,
    name text,
    photo bytea,
    engine_id int not null unique references engines(id)
);

--changset nikishin:add_history_owner
create table history_owner(
    id serial primary key,
    startAt timestamp,
    endAt timestamp,
    driver_id int not null references drivers(id),
    car_id int not null references cars(id)
);

--changset nikishin:add_car_id_references_cars(id)
alter table auto_posts add column car_id int references cars(id);

--changset nikishin:aletr_column_at_history_owner
alter table history_owner alter column startAt set DEFAULT now();
alter table history_owner alter column endAt set DEFAULT now();
