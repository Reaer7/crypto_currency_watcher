package com.gmail.brutskiart.watcher.controller;

import com.gmail.brutskiart.watcher.service.CryptoService;
import com.gmail.brutskiart.watcher.service.dto.CryptoDto;
import com.gmail.brutskiart.watcher.service.dto.CryptoWithPriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "1. Управление оповещениями пользователей об изменении цены")
public class CryptoController {

    private final CryptoService cryptoService;

    @Operation(summary = "Получить информацию о крипто монетах")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(examples = {
                    @ExampleObject(
                            name = "Получить информацию о крипто монетах",
                            value = SwaggerConstants.COINS_INFO
                    )
            })),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
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

    @Operation(summary = "Получить информацию о крипто монете по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(examples = {
                    @ExampleObject(
                            name = "Получить информацию о крипто монете по id",
                            value = SwaggerConstants.COIN_INFO
                    )
            })),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCryptoById(@PathVariable Long id) {
        Optional<CryptoWithPriceDto> optionalCrypto = cryptoService.get(id);
        return optionalCrypto.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
