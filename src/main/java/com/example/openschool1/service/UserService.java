package com.example.openschool1.service;

import com.example.openschool1.dto.CreateUserRequest;
import com.example.openschool1.dto.UserResponse;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public interface UserService {

    @NotNull
    UserResponse create(@NotNull CreateUserRequest request);

    @NotNull
    UserResponse update(@NotNull Integer userId, @NotNull CreateUserRequest request);

    void delete(@NotNull Integer userId);

    @NotNull
    List<UserResponse> findAll();

    @NotNull
    UserResponse findById(@NotNull Integer userId);
}
