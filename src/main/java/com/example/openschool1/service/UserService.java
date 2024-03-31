package com.example.openschool1.service;

import com.example.openschool1.dto.CreateUserRequest;
import com.example.openschool1.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse update(Long userId, CreateUserRequest request);

    void delete(Long userId);

    List<UserResponse> findAll();

    UserResponse findById(Long userId);
}
