package com.example.converter.controller;

import com.example.converter.model.User;
import com.example.converter.service.Implementation.UserServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping ("/api/v1/users")
public class UserController {

    private final UserServiceImplementation service;

    public UserController(UserServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("")
    public List<User> findAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteUserById(id);
    }
}
