package com.example.openschool1.service;

import com.example.openschool1.aspect.TrackAsyncTime;
import com.example.openschool1.aspect.TrackTime;
import com.example.openschool1.dto.AddressResponseDto;
import com.example.openschool1.dto.CreateAddressRequestDto;
import com.example.openschool1.dto.CreateUserRequestDto;
import com.example.openschool1.dto.UserResponseDto;
import com.example.openschool1.exception.NotFoundException;
import com.example.openschool1.model.Address;
import com.example.openschool1.model.User;
import com.example.openschool1.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @TrackTime
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @TrackTime
    public UserResponseDto findById(Long userId) {
        return userRepository.findById(userId)
                .map(this::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
    }

    @Override
    @Transactional
    @TrackAsyncTime
    public UserResponseDto create(CreateUserRequestDto request) {
        User user = buildUserRequest(request);
        return buildUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    @TrackAsyncTime
    public UserResponseDto update(Long userId, CreateUserRequestDto request) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID: " + userId + " not found"));
        userUpdate(user, request);
        return buildUserResponse(userRepository.save(user));
    }

    private void userUpdate(User user, CreateUserRequestDto request) {
        ofNullable(request.getLogin()).map(user::setLogin);
        ofNullable(request.getFirstName()).map(user::setFirstName);
        ofNullable(request.getMiddleName()).map(user::setMiddleName);
        ofNullable(request.getLastName()).map(user::setLastName);
        ofNullable(request.getAge()).map(user::setAge);

        CreateAddressRequestDto addressRequest = request.getAddress();
        if (addressRequest != null) {
            ofNullable(addressRequest.getBuilding()).map(user.getAddress()::setBuilding);
            ofNullable(addressRequest.getStreet()).map(user.getAddress()::setStreet);
            ofNullable(addressRequest.getCity()).map(user.getAddress()::setCity);
        }
    }

    @Override
    @Transactional
    @TrackAsyncTime
    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with ID: " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }

    private UserResponseDto buildUserResponse(User user) {
        return new UserResponseDto()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setAge(user.getAge())
                .setFirstName(user.getFirstName())
                .setMiddleName(user.getMiddleName())
                .setLastName(user.getLastName())
                .setAddress(new AddressResponseDto()
                        .setCity(user.getAddress().getCity())
                        .setBuilding(user.getAddress().getBuilding())
                        .setStreet(user.getAddress().getStreet()));
    }

    private User buildUserRequest(CreateUserRequestDto request) {
        return new User()
                .setLogin(request.getLogin())
                .setAge(request.getAge())
                .setFirstName(request.getFirstName())
                .setMiddleName(request.getMiddleName())
                .setLastName(request.getLastName())
                .setAddress(new Address()
                        .setCity(request.getAddress().getCity())
                        .setBuilding(request.getAddress().getBuilding())
                        .setStreet(request.getAddress().getStreet()));
    }
}
