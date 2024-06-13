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

    @GetMapping("{id}")
    public Optional<User> findById(@PathVariable("id") int id) {
        return service.getUserById(id);
    }

    @DeleteMapping("deleteById={id}")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteUserById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAll() {
        service.deleteAllUsers();
    }

    @PostMapping("save")
    public void save(@RequestBody User user){
        service.saveUser(user);
    }

    @PutMapping("updateById={id}")
    public void updateById(@PathVariable("id") int id, @RequestBody User user) {
        service.updateUserById(id, user);
    }
}
