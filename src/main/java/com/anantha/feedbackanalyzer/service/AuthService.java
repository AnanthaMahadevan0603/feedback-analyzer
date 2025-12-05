package com.anantha.feedbackanalyzer.service;

import com.anantha.feedbackanalyzer.dto.UserLoginRequest;
import com.anantha.feedbackanalyzer.dto.UserRegisterRequest;
import com.anantha.feedbackanalyzer.dto.UserResponse;

public interface AuthService {

    UserResponse register(UserRegisterRequest request);

    String login(UserLoginRequest request); // returns JWT token
}
