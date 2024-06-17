package com.example.converter.repository;

import com.example.converter.model.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, Integer> {
}
