package com.example.converter.service.Implementation;

import com.example.converter.model.Currency;
import com.example.converter.repository.CurrencyRepository;
import com.example.converter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImplementation implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImplementation(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Optional<Currency> getCurrencyById(String id) {
        currencyRepository.findById(id);
        return Optional.empty();
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public void deleteCurrencyById(String id){
        currencyRepository.deleteById(id);
    }

    public double getExchangeRate(String currencyFrom, String currencyTo) {
        if (currencyTo.equals("USD")) {
            return 0; //TODO
        }
        return 1; //TODO
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
