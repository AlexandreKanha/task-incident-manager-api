package com.alexandre.taskmanager.controller;

import com.alexandre.taskmanager.dto.CreateTaskRequest;
import com.alexandre.taskmanager.dto.TaskResponse;
import com.alexandre.taskmanager.entity.Task;
import com.alexandre.taskmanager.entity.TaskStatus;
import com.alexandre.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks", description = "Task management endpoints")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());

        Task saved = taskService.create(task, request.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @Operation(summary = "List all tasks")
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(
                taskService.findAll()
                        .stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @Operation(summary = "List tasks by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(
                taskService.findByStatus(status)
                        .stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @Operation(summary = "List tasks by user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponse>> getTasksByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                taskService.findByUser(userId)
                        .stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUser().getId(),
                task.getUser().getName()
        );
    }
}
