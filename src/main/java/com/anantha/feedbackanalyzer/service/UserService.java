package com.anantha.feedbackanalyzer.service;

import com.anantha.feedbackanalyzer.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();
}
