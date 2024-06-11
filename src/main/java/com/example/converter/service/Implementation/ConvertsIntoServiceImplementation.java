package com.example.converter.service.Implementation;

import com.example.converter.model.ConvertsInto;
import com.example.converter.repository.ConvertsIntoRepository;
import com.example.converter.service.ConvertsIntoService;
import com.example.converter.service.RestService;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class ConvertsIntoServiceImplementation implements ConvertsIntoService {

    @Value("${api.base.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final RestService restService = new RestService();

    private final ConvertsIntoRepository convertsIntoRepository;

    @Autowired
    public ConvertsIntoServiceImplementation(ConvertsIntoRepository convertsIntoRepository) {
        this.convertsIntoRepository = convertsIntoRepository;
    }

    @Override
    public Optional<ConvertsInto> getConvertsIntoById(int id) {
        return convertsIntoRepository.findById(id);
    }

    @Override
    public List<ConvertsInto> getAllConvertsInto() {
        return convertsIntoRepository.findAll();
    }

    @Override
    public void deleteConvertsIntoById(int id) {
        convertsIntoRepository.deleteById(id);
    }

    @Override
    public void deleteAllConvertsInto() { convertsIntoRepository.deleteAll(); }

    @Override
    public void saveConvertsInto(ConvertsInto convertsInto) { convertsIntoRepository.save(convertsInto); }

    @Override
    public void saveAllConvertsInto(List<ConvertsInto> convertsInto) { convertsIntoRepository.saveAll(convertsInto); }

    @Override
    public void updateConvertsIntoById(int id, ConvertsInto newConvertsInto) {
        if (convertsIntoRepository.findById(id).isEmpty()) {
            System.out.println("Error: ConvertsInto with id " + id + " not found");
            return;
        }
        ConvertsInto oldConvertsInto = convertsIntoRepository.findById(id).get();

            oldConvertsInto.setCurrencyA(newConvertsInto.getCurrencyA());

            oldConvertsInto.setCurrencyB(newConvertsInto.getCurrencyB());

            oldConvertsInto.setExchangeRate(newConvertsInto.getExchangeRate());

        convertsIntoRepository.save(oldConvertsInto);
    }

    @Override
    public void updateAllConvertsInto(List<ConvertsInto> newList) {
        for (int i = 0; i < 33; i++) {
            updateConvertsIntoById(i + 1, newList.get(i));
        }
    }

    public double getExchangeRate(String currencyFrom, String currencyTo) {
        HashMap<String,Double> map = new HashMap<>();
        List<ConvertsInto> list = convertsIntoRepository.findAll();
        for (ConvertsInto element : list) {
            map.put(element.getCurrencyB(), element.getExchangeRate());
        }

        return currencyTo.equals("USD") ?  1 / map.get(currencyFrom) : map.get(currencyTo);
    }

    @Override
    public double convert(String currencyFrom, String currencyTo, double amount) {
        if (currencyFrom.equals("USD") || currencyTo.equals("USD")) {
            System.out.println(amount + " " + currencyFrom + " into " + currencyTo + " : " + amount * getExchangeRate(currencyFrom, currencyTo));
            return amount * getExchangeRate(currencyFrom, currencyTo);
        }
        double result = convert(currencyFrom, "USD", amount);
        result = convert("USD", currencyTo, result);
        System.out.println(amount + " " + currencyFrom + " into " + currencyTo + " : " + result);
        return result;
    }

    @Scheduled(fixedRate = 60000000)
    public void updateExchanges() throws IOException, URISyntaxException, InterruptedException {
        updateAllConvertsInto(restService.getResult(restService.connect(apiKey,apiUrl)));
    }

}
