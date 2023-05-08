package com.gmail.brutskiart.watcher.repository.feign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoWithPrice {

    private Long id;
    private String symbol;

    @JsonProperty("price_usd")
    private BigDecimal priceUsd;
}
