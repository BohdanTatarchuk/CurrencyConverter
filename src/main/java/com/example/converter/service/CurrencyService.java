package com.example.converter.service;

import com.example.converter.model.Currency;
import com.example.converter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    public Optional<Currency> getCurrencyById(String id) {
        return currencyRepository.findById(id);
    }


    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }


    public void deleteCurrencyById(String id){
        currencyRepository.deleteById(id);
    }


    public void deleteAllCurrencies() {
        currencyRepository.deleteAll();
    }


    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }


    public void saveAllCurrencies(List<Currency> currencies) {
        currencyRepository.saveAll(currencies);
    }


    public void updateCurrencyById(String id, Currency newCurrency) {
        if (currencyRepository.findById(id).isEmpty()) {
            System.out.println("Error: Currency with id " + id + " not found");
            return;
        }

        Currency oldCurrency = currencyRepository.findById(id).get();
        oldCurrency.setName(newCurrency.getName());

        currencyRepository.save(oldCurrency);
    }
}
