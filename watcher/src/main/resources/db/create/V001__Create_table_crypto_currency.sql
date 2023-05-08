create table crypto_currency
(
    id        bigint,
    symbol    varchar(10) not null,
    price_usd float       null,
    primary key (id)
);
