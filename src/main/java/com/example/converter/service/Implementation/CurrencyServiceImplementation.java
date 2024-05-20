package com.example.converter.service.Implementation;

import com.example.converter.model.Currency;
import com.example.converter.service.CurrencyService;

import java.util.List;
import java.util.Optional;

public class CurrencyServiceImplementation implements CurrencyService {

    @Override
    public Currency createCurrency(Currency currency) {
        return currency; //TODO
    }

    @Override
    public Optional<Currency> getCurrencyById(String id) {
        return Optional.empty(); //TODO
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return null; //TODO
    }

    @Override
    public void deleteCurrencyById(String id){
        //TODO
    }

    public double getExchangeRate(String currencyFrom, String currencyTo) {
        if (currencyFrom.equals("USD")) {
            return 0; //TODO
        }
        return 0; //TODO
    }

    @Override
    public double convert(String currencyFrom, String currencyTo, double amount) {
        if (currencyFrom.equals("USD") || currencyTo.equals("USD")) {
            return amount * getExchangeRate(currencyFrom, currencyTo);
        }
        double result = convert(currencyFrom, "USD", amount);
        result = convert("USD", currencyTo, result);
        return result;
    }
}
