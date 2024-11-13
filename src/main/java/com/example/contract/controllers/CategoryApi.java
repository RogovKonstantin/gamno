package com.example.contract.controllers;

import com.example.contract.dtos.CategoryRequest;
import com.example.contract.dtos.CategoryResponse;
import com.example.contract.dtos.ListingResponse;
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

@Tag(name = "Categories API")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Request processed successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "404", description = "Listing not found",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class)))
})
public interface CategoryApi {

    @Operation(summary = "Get a category by ID")
    @GetMapping("/{id}")
    ResponseEntity<CategoryResponse> getCategoryById(@PathVariable UUID id);

    @Operation(summary = "Create a new category")
    @PostMapping
    ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest);

    @Operation(summary = "Update a category")
    @PatchMapping("/{id}")
    ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryRequest categoryRequest);

    @Operation(summary = "Delete a category by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable UUID id);
}
