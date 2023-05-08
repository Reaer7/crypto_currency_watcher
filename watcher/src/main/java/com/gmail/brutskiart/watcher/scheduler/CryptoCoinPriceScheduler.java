package com.gmail.brutskiart.watcher.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.brutskiart.watcher.repository.feign.CoinLoreRepository;
import com.gmail.brutskiart.watcher.repository.feign.model.CryptoWithPrice;
import com.gmail.brutskiart.watcher.repository.model.Crypto;
import com.gmail.brutskiart.watcher.service.CryptoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.gmail.brutskiart.watcher.config.ServerConstants.COIN_SERVER_IDS;
import static com.gmail.brutskiart.watcher.config.ServerConstants.DATA_KEY;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptoCoinPriceScheduler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CoinLoreRepository coinLoreRepository;
    private final CryptoService cryptoService;

    @Scheduled(cron = "0 * * * * *")
    public void pullPrice() {
        log.info("Update coin prices start");
        try {
            ResponseEntity<Object> responseEntity = coinLoreRepository.getCoins(COIN_SERVER_IDS);
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()) {
                Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
                String data = OBJECT_MAPPER.writeValueAsString(body.get(DATA_KEY));
                List<CryptoWithPrice> coins = OBJECT_MAPPER.readerForListOf(CryptoWithPrice.class)
                        .readValue(data);
                List<Crypto> cryptos = cryptoService.saveAll(coins);
                log.debug("Update coin prices succeeded!" + cryptos);
            } else {
                log.error("Can not be update coin prices!");
            }
        } catch (FeignException | JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
