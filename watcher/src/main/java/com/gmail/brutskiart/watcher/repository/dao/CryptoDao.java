package com.gmail.brutskiart.watcher.repository.dao;

import com.gmail.brutskiart.watcher.repository.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoDao extends JpaRepository<Crypto, Long> {

    Optional<Crypto> findBySymbolIgnoreCase(@Param("symbol") String symbol);
}
