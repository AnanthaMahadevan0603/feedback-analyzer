package com.anantha.feedbackanalyzer.service.impl;

import com.anantha.feedbackanalyzer.dto.UserResponse;
import com.anantha.feedbackanalyzer.entity.User;
import com.anantha.feedbackanalyzer.exception.ResourceNotFoundException;
import com.anantha.feedbackanalyzer.repository.UserRepository;
import com.anantha.feedbackanalyzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .roles(user.getRoles())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }
}
