package com.example.converter.service.Implementation;

import com.example.converter.model.User;
import com.example.converter.repository.UserRepository;
import com.example.converter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImplementation implements UserService {

    //@Autowired //TODO
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return null; //TODO
    }

    @Override
    public Optional<User> findUserById(int id) {
        return Optional.empty(); //TODO
    }

    @Override
    public List<User> getAllUsers() {
        return List.of(); //TODO
    }

    @Override
    public void deleteUserById(int id) {
        //TODO
    }
}
