set search_path to sample;

create table if not exists products
(
    id    bigserial not null primary key,
    name  varchar(255),
    price float8
);

create table if not exists users
(
    id    bigserial not null primary key,
    name  varchar(255),
    product_id bigint null
);
