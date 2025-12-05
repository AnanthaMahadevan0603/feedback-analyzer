package com.anantha.feedbackanalyzer.controller;

import com.anantha.feedbackanalyzer.dto.UserResponse;
import com.anantha.feedbackanalyzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
