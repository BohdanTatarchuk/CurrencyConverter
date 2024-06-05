package com.example.converter.service;

import com.example.converter.model.ConvertsInto;

import java.util.List;
import java.util.Optional;

public interface ConvertsIntoService {

    Optional<ConvertsInto> getConvertsIntoById(int id);

    List<ConvertsInto> getAllConvertsInto();

    void deleteConvertsIntoById(int id);

    void deleteAllConvertsInto();

    void saveConvertsInto(ConvertsInto convertsInto);

    void saveAllConvertsInto(List<ConvertsInto> convertsInto);

    void updateConvertsIntoById(int id, ConvertsInto convertsInto);

    void updateAllConvertsInto(List<ConvertsInto> convertsInto);

    double convert(String currencyFrom, String currencyTo, double amount);
}
