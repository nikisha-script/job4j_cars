--liquibase formatted sql

--changeset nikishin:insert_user
insert into auto_users(login,password) values('admin', 'admin');