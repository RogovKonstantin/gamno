package com.example.contract.controllers;

import com.example.contract.dtos.UserRequest;
import com.example.contract.dtos.UserResponse;
import com.example.contract.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RequestMapping("/users")
@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<UserResponse> getUserById(UUID id) {
        UserResponse user = findUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(new UserResponse(UUID.randomUUID(), userRequest.username(), userRequest.email()));
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UUID id, UserRequest userRequest) {
        return ResponseEntity.ok(new UserResponse(id, userRequest.username(), userRequest.email()));
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        return ResponseEntity.noContent().build();
    }

    private UserResponse findUserById(UUID id) {
        return new UserResponse(id, "User1", "user1@example.com");
    }
}
