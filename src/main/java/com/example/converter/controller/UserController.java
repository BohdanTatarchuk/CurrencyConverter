package com.example.converter.controller;

import com.example.converter.model.User;
import com.example.converter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping (path = "/api/v1/users/")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<User> findAll() {
        return service.getAllUsers();
    }

    @GetMapping("{login}")
    public Optional<User> findByLogin(@PathVariable("login") String login) {
        return service.getUserByLogin(login);
    }

    @DeleteMapping("deleteByLogin={login}")
    public void deleteByLogin(@PathVariable("login") String login) {
        service.deleteUserByLogin(login);
    }

    @DeleteMapping("deleteAll")
    public void deleteAll() {
        service.deleteAllUsers();
    }

    @PostMapping("save")
    public void save(@RequestBody User user){
        service.saveUser(user);
    }

    @PutMapping("updateByLogin={login}")
    public void updateByLogin(@PathVariable("login") String login, @RequestBody User user) {
        service.updateUserByLogin(login, user);
    }
}
