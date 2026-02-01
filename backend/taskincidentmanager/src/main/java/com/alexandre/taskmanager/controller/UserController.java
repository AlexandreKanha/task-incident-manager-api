package com.alexandre.taskmanager.controller;

import com.alexandre.taskmanager.entity.User;
import com.alexandre.taskmanager.service.UserService;
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
    public ResponseEntity<User> createUser(@RequestBody @org.springframework.lang.NonNull User user) {
        User created = userService.create(user);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @org.springframework.lang.NonNull Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
