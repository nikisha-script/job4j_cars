create table if not exists auto_users (
    id serial primary key,
    login text unique,
    password text
);

create table if not exists engines(
    id serial primary key,
    name varchar not null unique,
	power int not null
);

create table cars(
    id serial primary key,
    name text,
    photo bytea,
    engine_id int not null references engines(id)
);

create table if not exists auto_posts (
    id serial primary key,
    text text,
    created timestamp,
    is_sold bool default false,
    car_id int references cars(id),
    auto_user_id int references auto_users(id)
);