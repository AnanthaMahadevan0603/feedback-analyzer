package com.anantha.feedbackanalyzer.service.impl;

import com.anantha.feedbackanalyzer.dto.UserLoginRequest;
import com.anantha.feedbackanalyzer.dto.UserRegisterRequest;
import com.anantha.feedbackanalyzer.dto.UserResponse;
import com.anantha.feedbackanalyzer.entity.User;
import com.anantha.feedbackanalyzer.repository.UserRepository;
import com.anantha.feedbackanalyzer.security.JWTUtil;
import com.anantha.feedbackanalyzer.service.AuthService;
import com.anantha.feedbackanalyzer.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserResponse register(UserRegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of("USER"))
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public String login(UserLoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // ✅ Now token will contain roles too.
        return jwtUtil.generateToken(user.getEmail(), user.getRoles());
    }

}
