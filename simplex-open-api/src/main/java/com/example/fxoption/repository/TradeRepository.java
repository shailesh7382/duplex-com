// TradeRepository.java
package com.example.fxoption.repository;

import com.example.fxoption.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, String> {
}