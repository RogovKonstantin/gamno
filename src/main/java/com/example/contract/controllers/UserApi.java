package com.example.contract.controllers;

import com.example.contract.dtos.ListingResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Users API")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Request processed successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "404", description = "Listing not found",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class)))
})
public interface UserApi {

    @Operation(summary = "Get a user by ID")
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable UUID id);

    @Operation(summary = "Create a new user")
    @PostMapping
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest);

    @Operation(summary = "Update a user")
    @PatchMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequest userRequest);

    @Operation(summary = "Delete a user by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id);
}
