package com.example.converter.service;

import com.example.converter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(int id);

    List<User> getAllUsers();

    void deleteUserById(int id);

}
