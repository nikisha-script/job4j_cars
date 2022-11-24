--liquibase formatted sql

--changeset nikishin:create_auto_post
create table if not exists auto_posts (
    id serial primary key,
    text text,
    created timestamp,
    is_sold bool default false,
    car_id int references cars(id),
    auto_user_id int references auto_users(id)
);


comment on table auto_posts is 'Посты';
comment on column auto_posts.id is 'Идентификатор поста';
comment on column auto_posts.text is 'Текст поста';
comment on column auto_posts.created is 'Создание поста';
comment on column auto_posts.is_sold is 'Флаг о состоянии продажи машины';
comment on column auto_posts.car_id is 'Идентификатор машины';
comment on column auto_posts.auto_user_id is 'Идентификатор пользователя';