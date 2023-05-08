package com.gmail.brutskiart.watcher.service;

import com.gmail.brutskiart.watcher.repository.feign.model.CryptoWithPrice;
import com.gmail.brutskiart.watcher.repository.model.Crypto;
import com.gmail.brutskiart.watcher.service.dto.CryptoDto;
import com.gmail.brutskiart.watcher.service.dto.CryptoWithPriceDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CryptoService {

    Set<CryptoDto> get();

    Optional<CryptoDto> get(String symbol);

    Optional<CryptoWithPriceDto> get(Long id);

    List<Crypto> saveAll(List<CryptoWithPrice> coins);
}
