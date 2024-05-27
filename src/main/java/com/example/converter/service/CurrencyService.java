package com.example.converter.service;

import com.example.converter.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    Optional<Currency> getCurrencyById(String id);

    List<Currency> getAllCurrencies();

    void deleteCurrencyById(String id);

    void deleteAllCurrencies();

    void updateCurrencyById(String id, Currency currency);

    void saveCurrency(Currency currency);

    void saveAllCurrencies(List<Currency> currencies);

    double convert(String currencyFrom, String currencyTo, double amount);

}
