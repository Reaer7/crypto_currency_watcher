package com.gmail.brutskiart.watcher.repository.feign;

import com.gmail.brutskiart.watcher.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "CoinLore",
        url = "${url.coin.lore}",
        configuration = FeignConfig.class
)
public interface CoinLoreRepository {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> getCoins(@RequestParam("id") Integer... ids);
}
