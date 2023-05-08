package com.gmail.brutskiart.watcher.service.mapper;

import com.gmail.brutskiart.watcher.repository.feign.model.CryptoWithPrice;
import com.gmail.brutskiart.watcher.repository.model.Crypto;
import com.gmail.brutskiart.watcher.service.dto.CryptoDto;
import com.gmail.brutskiart.watcher.service.dto.CryptoWithPriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CryptoMapper {

    CryptoDto toDto(Crypto crypto);

    CryptoWithPriceDto toWithPriceDto(Crypto crypto);

    Crypto toEntity(CryptoWithPrice crypto);
}
