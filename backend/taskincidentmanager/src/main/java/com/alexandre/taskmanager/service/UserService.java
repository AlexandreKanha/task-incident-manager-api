package com.alexandre.taskmanager.service;

import com.alexandre.taskmanager.entity.User;
import com.alexandre.taskmanager.repository.UserRepository;
import com.alexandre.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.alexandre.taskmanager.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
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

    public void delete(@org.springframework.lang.NonNull Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        // Deletar todas as tasks associadas ao usuário primeiro
        var tasks = taskRepository.findByUserId(id);
        if (tasks != null && !tasks.isEmpty()) {
            taskRepository.deleteAll(tasks);
        }
        // Agora deletar o usuário
        userRepository.deleteById(id);
    }
}