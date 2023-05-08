package com.gmail.brutskiart.watcher.service.impl;

import com.gmail.brutskiart.watcher.exception.SymbolDeniedException;
import com.gmail.brutskiart.watcher.repository.dao.CryptoDao;
import com.gmail.brutskiart.watcher.repository.dao.UserNotifyDao;
import com.gmail.brutskiart.watcher.repository.model.Crypto;
import com.gmail.brutskiart.watcher.repository.model.UserNotify;
import com.gmail.brutskiart.watcher.service.UserService;
import com.gmail.brutskiart.watcher.service.dto.UserNotifyDto;
import com.gmail.brutskiart.watcher.service.mapper.UserNotifyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CryptoDao cryptoDao;
    private final UserNotifyMapper userNotifyMapper;
    private final UserNotifyDao userNotifyDao;

    @Override
    @Transactional
    public UserNotifyDto post(UserNotifyDto userNotifyDto) {
        String symbol = userNotifyDto.getSymbol();
        Optional<Crypto> optionalCrypto = cryptoDao.findBySymbolIgnoreCase(symbol);
        if (optionalCrypto.isEmpty()) {
            throw new SymbolDeniedException();
        }
        Crypto crypto = optionalCrypto.get();

        UserNotify userNotify = getUserNotify(userNotifyDto, crypto);
        userNotify.setPriceUsd(crypto.getPriceUsd());
        UserNotify savedUserNotify = userNotifyDao.save(userNotify);
        return userNotifyMapper.toDto(savedUserNotify);
    }

    private UserNotify getUserNotify(UserNotifyDto userNotifyDto, Crypto crypto) {
        Optional<UserNotify> optionalUserNotify = userNotifyDao.findByUsernameAndCrypto_SymbolAllIgnoreCase(
                userNotifyDto.getUsername(),
                userNotifyDto.getSymbol()
        );
        if (optionalUserNotify.isEmpty()) {
            UserNotify userNotify = userNotifyMapper.toEntity(userNotifyDto);
            userNotify.setCrypto(crypto);
            return userNotify;
        } else {
            return optionalUserNotify.get();
        }
    }
}
