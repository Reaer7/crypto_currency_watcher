package com.gmail.brutskiart.watcher.service.impl;

import com.gmail.brutskiart.watcher.repository.dao.CryptoDao;
import com.gmail.brutskiart.watcher.repository.feign.CoinLoreRepository;
import com.gmail.brutskiart.watcher.repository.model.Crypto;
import com.gmail.brutskiart.watcher.service.CryptoService;
import com.gmail.brutskiart.watcher.service.mapper.CryptoMapper;
import com.gmail.brutskiart.watcher.service.dto.CryptoDto;
import com.gmail.brutskiart.watcher.service.dto.CryptoWithPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final CryptoDao cryptoDao;
    private final CryptoMapper cryptoMapper;
    private final CoinLoreRepository coinLoreRepository;

    @Override
    public Set<CryptoDto> get() {
        List<Crypto> cryptos = cryptoDao.findAll();
        return cryptos.stream()
                .map(cryptoMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<CryptoDto> get(String symbol) {
        Optional<Crypto> optionalCrypto = cryptoDao.findBySymbolIgnoreCase(symbol);
        if (optionalCrypto.isPresent()) {
            return optionalCrypto.map(cryptoMapper::toDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CryptoWithPriceDto> get(Long id) {
        Optional<Crypto> optionalCrypto = cryptoDao.findById(id);
        if (optionalCrypto.isPresent()) {
            return optionalCrypto.map(cryptoMapper::toWithPriceDto);
        } else {
            return Optional.empty();
        }
    }
}
