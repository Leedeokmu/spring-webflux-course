create table users
(
    id      serial primary key,
    name    varchar(50),
    balance int
);

create table user_transaction
(
    id               serial primary key,
    user_id          bigint,
    amount           int,
    transaction_date timestamp
);

insert into users(name, balance)
values ('sam', 1000),
       ('mike', 1200),
       ('jake', 800),
       ('martial', 700);
