package com.anantha.feedbackanalyzer.controller;

import com.anantha.feedbackanalyzer.dto.UserLoginRequest;
import com.anantha.feedbackanalyzer.dto.UserRegisterRequest;
import com.anantha.feedbackanalyzer.dto.UserResponse;
import com.anantha.feedbackanalyzer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        return authService.login(request);
    }
}
