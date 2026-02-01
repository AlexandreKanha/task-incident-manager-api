package com.alexandre.taskmanager.controller;

import com.alexandre.taskmanager.dto.CreateUserRequest;
import com.alexandre.taskmanager.dto.UserResponse;
import com.alexandre.taskmanager.entity.User;
import com.alexandre.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(
                userService.findAll()
                        .stream()
                        .map(u -> new UserResponse(u.getId(), u.getName(), u.getEmail()))
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(
                new UserResponse(user.getId(), user.getName(), user.getEmail())
        );
    }
}
