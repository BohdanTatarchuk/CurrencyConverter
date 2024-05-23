package com.example.converter.service.Implementation;

import com.example.converter.model.ConvertsInto;
import com.example.converter.repository.ConvertsIntoRepository;
import com.example.converter.service.ConvertsIntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvertsIntoServiceImplementation implements ConvertsIntoService {

    private final ConvertsIntoRepository convertsIntoRepository;

    @Autowired
    public ConvertsIntoServiceImplementation(ConvertsIntoRepository convertsIntoRepository) {
        this.convertsIntoRepository = convertsIntoRepository;
    }

    public Optional<ConvertsInto> getConvertsIntoById(int id) {
        convertsIntoRepository.findById(id);
        return Optional.empty();
    }

    @Override
    public List<ConvertsInto> getAllConvertsInto() {
        return convertsIntoRepository.findAll();
    }

    @Override
    public void deleteConvertsIntoById(int id) {
        convertsIntoRepository.deleteById(id);
    }
}
