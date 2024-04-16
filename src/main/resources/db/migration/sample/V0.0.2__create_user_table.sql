set search_path to sample;

create table if not exists users
(
    id    bigserial    not null,
    email varchar(255) not null,
    name  varchar(255),
    primary key (id, email)
);
