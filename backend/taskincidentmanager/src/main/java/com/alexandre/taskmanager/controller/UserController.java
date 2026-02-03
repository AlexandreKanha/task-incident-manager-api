package com.alexandre.taskmanager.controller;

import com.alexandre.taskmanager.dto.CreateUserRequest;
import com.alexandre.taskmanager.dto.UserResponse;
import com.alexandre.taskmanager.entity.User;
import com.alexandre.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "User management endpoints")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User saved = userService.create(user);

        return ResponseEntity.ok(
                new UserResponse(saved.getId(), saved.getName(), saved.getEmail())
        );
    }

    @Operation(summary = "List all users")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(
                userService.findAll()
                        .stream()
                        .map(u -> new UserResponse(u.getId(), u.getName(), u.getEmail()))
                        .toList()
        );
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(
                new UserResponse(user.getId(), user.getName(), user.getEmail())
        );
    }

    @Operation(summary = "Delete a user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @org.springframework.lang.NonNull Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
