package com.example.converter.controller;

import com.example.converter.model.Currency;
import com.example.converter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/currencies/")
public class CurrencyController {

    private final CurrencyService service;

    @Autowired
    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Currency> getAllCurrencies() {
        return service.getAllCurrencies();
    }

    @GetMapping("{id}")
    public Optional<Currency> getCurrencyById(@PathVariable("id") String id) {
        return service.getCurrencyById(id);
    }

    @DeleteMapping("deleteById={id}")
    public void deleteCurrency(@PathVariable("id") String id){
        service.deleteCurrencyById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllCurrencies(){
        service.deleteAllCurrencies();
    }

    @PutMapping("updateById={id}")
    public void updateCurrency(@PathVariable("id") String id, @RequestBody Currency currency){
        service.updateCurrencyById(id,currency);
    }

    @PostMapping("save")
    public void addCurrency(@RequestBody Currency currency){
        service.saveCurrency(currency);
    }

    @PostMapping("saveAll")
    public void addAllCurrencies(@RequestBody List<Currency> currencies){
        service.saveAllCurrencies(currencies);
    }
}
