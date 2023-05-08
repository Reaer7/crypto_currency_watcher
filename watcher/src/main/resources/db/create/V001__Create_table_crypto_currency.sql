create table if not exists crypto_currency
(
    id        bigint,
    symbol    varchar(20) not null unique,
    price_usd numeric     null,
    primary key (id)
);
