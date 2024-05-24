package com.example.converter.controller;

import com.example.converter.model.Currency;
import com.example.converter.service.Implementation.CurrencyServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/currencies")
public class CurrencyController {

    private final CurrencyServiceImplementation service;

    public CurrencyController(CurrencyServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Currency> getAllCurrencies() {
        return service.getAllCurrencies();
    }

    @GetMapping("/{id}")
    public Optional<Currency> getCurrencyById(@PathVariable String id) {
        return service.getCurrencyById(id);
    }

    @DeleteMapping("/")
    public void deleteCurrency(String id){
        service.deleteCurrencyById(id);
    }
}
