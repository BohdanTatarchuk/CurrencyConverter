package com.example.converter.service;

import com.example.converter.model.ConvertsInto;

import java.util.List;
import java.util.Optional;

public interface ConvertsIntoService {

    Optional<ConvertsInto> getConvertsIntoById(int id);

    List<ConvertsInto> getAllConvertsInto();

    void deleteConvertsIntoById(int id);
}
