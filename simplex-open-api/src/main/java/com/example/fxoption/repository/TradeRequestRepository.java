// TradeRequestRepository.java
package com.example.fxoption.repository;

import com.example.fxoption.entity.TradeRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRequestRepository extends JpaRepository<TradeRequestEntity, String> {
}