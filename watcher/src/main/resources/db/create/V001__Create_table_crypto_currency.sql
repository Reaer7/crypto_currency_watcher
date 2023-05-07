create table crypto_currency
(
    id                int,
    symbol            varchar(3)   not null,
    price_usd         float        null,
    primary key (id)
);
