--liquibase formatted sql

--changeset nikishin:create_cars
create table if not exists cars(
    id serial primary key,
    name text,
    photo bytea,
    engine_id int not null references engines(id)
);


comment on table cars is 'Машины';
comment on column cars.id is 'Идентификатор машины';
comment on column cars.name is 'Название машины';
comment on column cars.photo is 'Фото машины';
comment on column cars.engine_id is 'Идентификатор двигателя';