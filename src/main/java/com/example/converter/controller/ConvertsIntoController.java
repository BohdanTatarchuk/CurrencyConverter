package com.example.converter.controller;

import com.example.converter.model.ConvertsInto;
import com.example.converter.service.ConvertsIntoService;
import com.example.converter.service.Implementation.ConvertsIntoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/exchanges/")
public class ConvertsIntoController {

    private final ConvertsIntoService service;

    @Autowired
    public ConvertsIntoController(ConvertsIntoServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("")
    public List<ConvertsInto> getAllConvertsInto() {
        return service.getAllConvertsInto();
    }

    @GetMapping("{id}")
    public Optional<ConvertsInto> getConvertsIntoById(@PathVariable("id") int id) {
        return service.getConvertsIntoById(id);
    }

    @DeleteMapping("{id}")
    public void deleteConvertsIntoById(@PathVariable("id") int id) {
        service.deleteConvertsIntoById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllConvertsInto() {
        service.deleteAllConvertsInto();
    }

    @PostMapping("save")
    public void addConvertsInto(@RequestBody ConvertsInto convertsInto) {
        service.saveConvertsInto(convertsInto);
    }

    @PostMapping("saveAll")
    public void addAllConvertsInto(@RequestBody List<ConvertsInto> list) {
        service.saveAllConvertsInto(list);
    }

    @PutMapping("updateById={id}")
    public void updateConvertsInto(@PathVariable("id")int id, @RequestBody ConvertsInto convertsInto) {
        service.updateConvertsIntoById(id, convertsInto);
    }

    @PutMapping("updateAll")
    public void updateConvertsInto(@RequestBody List<ConvertsInto> list) {
        service.updateAllConvertsInto(list);
    }
}
