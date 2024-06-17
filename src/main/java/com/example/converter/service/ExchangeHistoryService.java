package com.example.converter.service;

import com.example.converter.model.ConvertsInto;
import com.example.converter.model.ExchangeHistory;
import com.example.converter.model.User;
import com.example.converter.repository.ExchangeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeHistoryService {

    private ExchangeHistoryRepository exchangeHistoryRepository;

    @Autowired
    ExchangeHistoryService(ExchangeHistoryRepository exchangeHistoryRepository) {
        this.exchangeHistoryRepository = exchangeHistoryRepository;
    }

    public Optional<ExchangeHistory> getExchangeHistoryById(int id) {
        return exchangeHistoryRepository.findById(id);
    }

    public List<ExchangeHistory> getAllExchangeHistory() {
        return exchangeHistoryRepository.findAll();
    }

    public void deleteExchangeHistoryById(int id) {
        exchangeHistoryRepository.deleteById(id);
    }

    public void deleteAllExchangeHistory() {
        exchangeHistoryRepository.deleteAll();
    }

    public void saveExchangeHistory(ExchangeHistory exchangeHistory) {
        exchangeHistoryRepository.save(exchangeHistory);
    }

    public void saveAllExchangeHistory(List<ExchangeHistory> exchangeHistories) {
        exchangeHistoryRepository.saveAll(exchangeHistories);
    }

    //@Scheduled(fixedRate = 60000)
    //public void test(){
    //    LocalDateTime localDateTime = LocalDateTime.now();
    //    ExchangeHistory exchangeHistory = new ExchangeHistory(
    //            new User("asdasd", "asdasdas"),
    //            new ConvertsInto("USD","USD",12.90),
    //            localDateTime
    //    );
    //    saveExchangeHistory(exchangeHistory);
    //}
    //Maybe, modify functions save and saveAll, so that they will automatically find user and convertsInto?
}
