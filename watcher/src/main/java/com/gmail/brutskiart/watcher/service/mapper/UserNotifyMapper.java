package com.gmail.brutskiart.watcher.service.mapper;

import com.gmail.brutskiart.watcher.repository.model.UserNotify;
import com.gmail.brutskiart.watcher.service.dto.UserNotifyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserNotifyMapper {

    @Mapping(target = "symbol", source = "crypto.symbol")
    UserNotifyDto toDto(UserNotify userNotify);

    UserNotify toEntity(UserNotifyDto userNotify);
}
