package com.alexandre.taskmanager.service;

import com.alexandre.taskmanager.entity.Task;
import com.alexandre.taskmanager.entity.TaskStatus;
import com.alexandre.taskmanager.entity.User;
import com.alexandre.taskmanager.repository.TaskRepository;
import com.alexandre.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task create(Task task, @org.springframework.lang.NonNull Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);
        task.setStatus(TaskStatus.OPEN);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> findByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }
}
