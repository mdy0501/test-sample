set search_path to sample;

create table if not exists products
(
    id         varchar(255) not null primary key,
    name       varchar(255),
    price      bigint
);
