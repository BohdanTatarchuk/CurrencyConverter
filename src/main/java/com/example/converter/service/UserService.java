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

    public Optional<User> getUserById(int id) {
       return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public void saveAllUsers(List<User> users){
        userRepository.saveAll(users);
    }

    public void updateUserById(int id, User newUser){
        if (userRepository.findById(id).isEmpty()) {
            System.out.println("Error: User with id " + id + " not found");
            return;
        }
        User oldUser = userRepository.findById(id).get();

        if (!newUser.getLogin().isEmpty()) {
            oldUser.setLogin(newUser.getLogin());
        }
        if (!newUser.getPassword().isEmpty()) {
            oldUser.setPassword(newUser.getPassword());
        }

        userRepository.save(oldUser);
    }

}
