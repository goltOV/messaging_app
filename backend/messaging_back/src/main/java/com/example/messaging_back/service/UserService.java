package com.example.messaging_back.service;


import com.example.messaging_back.entity.User;
import com.example.messaging_back.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    @Transactional
    public User createUser(String username, String email, String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        //TODO:
        // add hashing password
        user.setPasswordHash(password);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }
}
