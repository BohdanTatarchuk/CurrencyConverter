package com.example.converter.service.Implementation;

import com.example.converter.model.User;
import com.example.converter.repository.UserRepository;
import com.example.converter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserById(int id) {
       userRepository.findById(id);
       return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
