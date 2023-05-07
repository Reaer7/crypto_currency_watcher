package com.gmail.brutskiart.watcher.controller;

import com.gmail.brutskiart.watcher.service.CryptoService;
import com.gmail.brutskiart.watcher.service.dto.CryptoDto;
import com.gmail.brutskiart.watcher.service.dto.CryptoWithPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/crypto",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping
    public ResponseEntity<Object> getCrypto(@RequestParam(required = false) String symbol) {
        if (symbol != null) {
            Optional<CryptoDto> optionalCrypto = cryptoService.get(symbol);
            return optionalCrypto.<ResponseEntity<Object>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            Set<CryptoDto> cryptos = cryptoService.get();
            return ResponseEntity.ok(cryptos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCryptoById(@PathVariable Long id) {
        Optional<CryptoWithPriceDto> optionalCrypto = cryptoService.get(id);
        return optionalCrypto.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
