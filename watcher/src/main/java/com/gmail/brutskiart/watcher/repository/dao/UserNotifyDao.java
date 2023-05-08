package com.gmail.brutskiart.watcher.repository.dao;

import com.gmail.brutskiart.watcher.repository.model.UserNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserNotifyDao extends JpaRepository<UserNotify, Long> {

    Optional<UserNotify> findByUsernameAndCrypto_SymbolAllIgnoreCase(
            @Param("username") String username,
            @Param("symbol") String symbol
    );
}
