package com.gmail.brutskiart.watcher.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.brutskiart.watcher.event.PulledCoinPriceEvent;
import com.gmail.brutskiart.watcher.repository.feign.CoinLoreRepository;
import com.gmail.brutskiart.watcher.repository.feign.model.CryptoWithPrice;
import com.gmail.brutskiart.watcher.service.CryptoService;
import com.gmail.brutskiart.watcher.service.dto.CryptoWithPriceDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.gmail.brutskiart.watcher.service.ServerConstants.COIN_SERVER_IDS;
import static com.gmail.brutskiart.watcher.service.ServerConstants.DATA_KEY;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptoCoinPriceScheduler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CoinLoreRepository coinLoreRepository;
    private final CryptoService cryptoService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "${update.coin.price.cron.expression}")
    public void pullPrice() {
        log.info("Update coin prices start");
        try {
            ResponseEntity<Object> responseEntity = coinLoreRepository.getCoins(COIN_SERVER_IDS);
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()) {
                Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
                Object data = body.get(DATA_KEY);
                List<CryptoWithPrice> coins = OBJECT_MAPPER.readerForListOf(CryptoWithPrice.class)
                        .readValue(OBJECT_MAPPER.writeValueAsString(data));
                List<CryptoWithPriceDto> cryptosWithPrice = cryptoService.saveAll(coins);
                log.debug("Update coin prices succeeded!" + cryptosWithPrice);

                PulledCoinPriceEvent pulledCoinPriceEvent = new PulledCoinPriceEvent(this);
                applicationEventPublisher.publishEvent(pulledCoinPriceEvent);
            } else {
                log.error("Can not be update coin prices!");
            }
        } catch (FeignException | JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
