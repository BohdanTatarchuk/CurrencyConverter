package com.example.converter.controller;

import com.example.converter.model.ConvertsInto;
import com.example.converter.service.Implementation.ConvertsIntoServiceImplementation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/currencies")
public class ConvertsIntoController {

    private final ConvertsIntoServiceImplementation service;

    public ConvertsIntoController(ConvertsIntoServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("")
    public List<ConvertsInto> getAllConvertsInto() {
        return service.getAllConvertsInto();
    }

    @GetMapping("/{id}")
    public Optional<ConvertsInto> getConvertsIntoById(@PathVariable int id) {
        return service.getConvertsIntoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteConvertsIntoById(@PathVariable int id) {
        service.deleteConvertsIntoById(id);
    }
}
