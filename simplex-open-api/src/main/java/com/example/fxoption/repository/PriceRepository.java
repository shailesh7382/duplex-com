// PriceRepository.java
package com.example.fxoption.repository;

import com.example.fxoption.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, String> {
}