--liquibase formatted sql

--changeset nikishin:create_engine
create table if not exists engines(
    id serial primary key,
    name varchar not null unique,
	power int not null
);


comment on table engines is 'Двигатели';
comment on column engines.id is 'Идентификатор двигателя';
comment on column engines.name is 'Название двигателя';
comment on column engines.power is 'Мощность двигателя';