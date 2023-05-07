package com.gmail.brutskiart.watcher.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CryptoWithPriceDto {

    private Long id;
    private String symbol;
    private BigDecimal priceUsd;
}
