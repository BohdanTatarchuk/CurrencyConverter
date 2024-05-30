package com.example.converter.service.Implementation;

import com.example.converter.model.ConvertsInto;
import com.example.converter.repository.ConvertsIntoRepository;
import com.example.converter.service.ConvertsIntoService;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

        if (!newConvertsInto.getCurrencyA().isEmpty()){
            oldConvertsInto.setCurrencyA(newConvertsInto.getCurrencyA());
        }
        if (!newConvertsInto.getCurrencyB().isEmpty()){
            oldConvertsInto.setCurrencyB(newConvertsInto.getCurrencyB());
        }
        if (newConvertsInto.getExchangeRate() != 0){
            oldConvertsInto.setExchangeRate(newConvertsInto.getExchangeRate());
        }
        convertsIntoRepository.save(oldConvertsInto);
    }

    @SQL("ORDER BY exchange_id")
    public void sort() {}

    @Override
    public void updateAllConvertsInto(List<ConvertsInto> newList) {
        List<ConvertsInto> oldList = convertsIntoRepository.findAll();

        for (int i = 0; i < oldList.size(); i++) {
            if (newList.get(i).getExchangeRate() != oldList.get(i).getExchangeRate()) {
                oldList.get(i).setExchangeRate(newList.get(i).getExchangeRate());
            }
        }

        sort();

        convertsIntoRepository.saveAll(oldList);
    }
}
