// PriceRequestRepository.java
package com.example.fxoption.repository;

import com.example.fxoption.entity.PriceRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRequestRepository extends JpaRepository<PriceRequestEntity, String> {
}