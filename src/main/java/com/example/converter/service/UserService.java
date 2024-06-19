package com.example.converter.service;

import com.example.converter.model.User;
import com.example.converter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByLogin(String login) {
       return userRepository.findById(login);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserByLogin(String login) {
        userRepository.deleteById(login);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public void updateUserByLogin(String login, User newUser){
        if (userRepository.findById(login).isEmpty()) {
            System.out.println("Error: User with login " + login + " not found");
            return;
        }

        User oldUser = userRepository.findById(login).get();

        if (!newUser.getLogin().isEmpty()) {
            oldUser.setLogin(newUser.getLogin());
        }
        if (!newUser.getPassword().isEmpty()) {
            oldUser.setPassword(newUser.getPassword());
        }

        userRepository.save(oldUser);
    }

}
