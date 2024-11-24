package com.example.contract.controllers;

import com.example.contract.dtos.UserRequest;
import com.example.contract.dtos.UserResponse;
import com.example.contract.exceptions.ExceptionHandlers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Users API")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Request processed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class)))
})
public interface UserApi {

    @Operation(summary = "Get user by ID")
    @GetMapping("/users/{id}")
    ResponseEntity<EntityModel<UserResponse>> getUserById(@PathVariable UUID id);

    @Operation(summary = "Create a new user")
    @PostMapping("/users")
    ResponseEntity<EntityModel<UserResponse>> createUser(@RequestBody @Valid UserRequest userRequest);

    @Operation(summary = "Get all users with pagination")
    @GetMapping("/users")
    ResponseEntity<PagedModel<EntityModel<UserResponse>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    );

    @Operation(summary = "Update a user")
    @PatchMapping("/users/{id}")
    ResponseEntity<EntityModel<UserResponse>> patchUser(
            @PathVariable UUID id,
            @RequestBody @Valid UserRequest userRequest
    );

    @Operation(summary = "Delete a user")
    @DeleteMapping("/users/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id);
}
