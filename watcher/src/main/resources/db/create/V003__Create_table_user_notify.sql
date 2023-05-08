create table if not exists user_notify
(
    id        bigserial,
    username  varchar(255) not null,
    crypto_id bigint       not null,
    price_usd numeric      not null,
    primary key (id),
    unique (username, crypto_id),
    foreign key (crypto_id) references crypto_currency (id)
);
