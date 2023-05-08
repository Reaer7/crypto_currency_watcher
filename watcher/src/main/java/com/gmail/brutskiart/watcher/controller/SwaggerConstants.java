package com.gmail.brutskiart.watcher.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SwaggerConstants {

    public static final String CREATE_NOTIFY = """
            {
                "username": "username",
                "symbol": "ETH"
            }
            """;
    public static final String COIN_INFO = """
            {
                 "id": 90,
                 "symbol": "BTC",
                 "priceUsd": 100000.00
             }
            """;
    public static final String COINS_INFO = """
            [
                {
                    "id": 90,
                    "symbol": "BTC",
                    "priceUsd": 100000.00
                },
                {
                    "id": 80,
                    "symbol": "ETH",
                    "priceUsd": 10000.00
                }
            ]
            """;
}
