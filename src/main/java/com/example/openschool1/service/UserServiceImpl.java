package com.example.openschool1.service;

import com.example.openschool1.dto.AddressResponse;
import com.example.openschool1.dto.CreateAddressRequest;
import com.example.openschool1.dto.CreateUserRequest;
import com.example.openschool1.dto.UserResponse;
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
    private static UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {
        return userRepository.findById(userId)
                .map(this::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
    }

    @Override
    @Transactional
    public UserResponse create(CreateUserRequest request) {
        User user = buildUserRequest(request);
        return buildUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse update(Long userId, CreateUserRequest request) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
        userUpdate(user, request);
        return buildUserResponse(userRepository.save(user));
    }

    private void userUpdate(User user, CreateUserRequest request) {
        ofNullable(request.getLogin()).map(user::setLogin);
        ofNullable(request.getFirstName()).map(user::setFirstName);
        ofNullable(request.getMiddleName()).map(user::setMiddleName);
        ofNullable(request.getLastName()).map(user::setLastName);
        ofNullable(request.getAge()).map(user::setAge);

        CreateAddressRequest addressRequest = request.getAddress();
        if (addressRequest != null) {
            ofNullable(addressRequest.getBuilding()).map(user.getAddress()::setBuilding);
            ofNullable(addressRequest.getStreet()).map(user.getAddress()::setStreet);
            ofNullable(addressRequest.getCity()).map(user.getAddress()::setCity);
        }
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    private UserResponse buildUserResponse(User user) {
        return new UserResponse()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setAge(user.getAge())
                .setFirstName(user.getFirstName())
                .setMiddleName(user.getMiddleName())
                .setLastName(user.getLastName())
                .setAddress(new AddressResponse()
                        .setCity(user.getAddress().getCity())
                        .setBuilding(user.getAddress().getBuilding())
                        .setStreet(user.getAddress().getStreet()));
    }

    private User buildUserRequest(CreateUserRequest request) {
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
