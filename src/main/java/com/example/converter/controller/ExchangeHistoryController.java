package com.example.converter.controller;

import com.example.converter.model.ExchangeHistory;
import com.example.converter.service.ExchangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/history")
public class ExchangeHistoryController {
    private final ExchangeHistoryService service;

    @Autowired
    public ExchangeHistoryController(ExchangeHistoryService service) {
        this.service = service;
    }

    @RequestMapping("all")
    public List<ExchangeHistory> getAllExchangeHistory(){
        return service.getAllExchangeHistory();
    }

    @RequestMapping("{id}")
    public Optional<ExchangeHistory> getExchangeHistoryById(@PathVariable("id") int id){
        return service.getExchangeHistoryById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllExchangeHistory(){
        service.deleteAllExchangeHistory();
    }

    @DeleteMapping("deleteById={id}")
    public void deleteExchangeHistoryById(@PathVariable("id") int id){
        service.deleteExchangeHistoryById(id);
    }

    @PostMapping("save")
    public void addExchangeHistory(@RequestBody ExchangeHistory exchangeHistory){
        service.saveExchangeHistory(exchangeHistory);
    }

    @PostMapping("saveAll")
    public void addAllExchangeHistory(@RequestBody List<ExchangeHistory> exchangeHistoryList){
        service.saveAllExchangeHistory(exchangeHistoryList);
    }
}
