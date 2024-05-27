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
    public Optional<User> getUserById(int id) {
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

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @Override
    public void saveAllUsers(List<User> users){
        userRepository.saveAll(users);
    }

    @Override
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
