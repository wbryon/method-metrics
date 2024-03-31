package com.example.openschool1.service;

import com.example.openschool1.dto.CreateUserRequest;
import com.example.openschool1.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public UserResponse create(CreateUserRequest request) {
        return null;
    }

    @Override
    public UserResponse update(Integer userId, CreateUserRequest request) {
        return null;
    }

    @Override
    public void delete(Integer userId) {

    }

    @Override
    public List<UserResponse> findAll() {
        return null;
    }

    @Override
    public UserResponse findById(Integer userId) {
        return null;
    }
}
