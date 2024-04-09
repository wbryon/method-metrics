package com.example.openschool1.controller;

import com.example.openschool1.dto.CreateUserRequestDto;
import com.example.openschool1.dto.UserResponseDto;
import com.example.openschool1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public UserResponseDto findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponseDto create(@RequestBody @Valid CreateUserRequestDto request) {
        return userService.create(request);
    }

    @PatchMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponseDto update(@PathVariable Long userId, @RequestBody @Valid CreateUserRequestDto request) {
        return userService.update(userId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
