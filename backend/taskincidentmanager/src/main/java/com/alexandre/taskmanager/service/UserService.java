package com.alexandre.taskmanager.service;

import com.alexandre.taskmanager.entity.User;
import com.alexandre.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.alexandre.taskmanager.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(@org.springframework.lang.NonNull User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
    if (id == null) {
        throw new IllegalArgumentException("User id must not be null");
    }
    return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}