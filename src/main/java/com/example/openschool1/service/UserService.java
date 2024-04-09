package com.example.openschool1.service;

import com.example.openschool1.dto.CreateUserRequestDto;
import com.example.openschool1.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto create(CreateUserRequestDto request);

    UserResponseDto update(Long userId, CreateUserRequestDto request);

    void delete(Long userId);

    List<UserResponseDto> findAll();

    UserResponseDto findById(Long userId);
}
