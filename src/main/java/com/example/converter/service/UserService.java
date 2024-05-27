package com.example.converter.service;

import com.example.converter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(int id);

    List<User> getAllUsers();

    void deleteUserById(int id);

    void deleteAllUsers();

    void saveUser(User user);

    void saveAllUsers(List<User> users);

    void updateUserById(int id, User user);

}
