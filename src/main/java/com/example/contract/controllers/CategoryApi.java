package com.example.contract.controllers;

import com.example.contract.dtos.CategoryRequest;
import com.example.contract.dtos.CategoryResponse;
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

@Tag(name = "Categories API")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Request processed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "404", description = "Category not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class)))
})
public interface CategoryApi {

    @Operation(summary = "Get category by ID")
    @GetMapping("/categories/{id}")
    ResponseEntity<EntityModel<CategoryResponse>> getCategoryById(@PathVariable UUID id);

    @Operation(summary = "Create a new category")
    @PostMapping("/categories")
    ResponseEntity<EntityModel<CategoryResponse>> createCategory(@RequestBody @Valid CategoryRequest categoryRequest);

    @Operation(summary = "Get all categories with pagination")
    @GetMapping("/categories")
    ResponseEntity<PagedModel<EntityModel<CategoryResponse>>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    );

    @Operation(summary = "Update a category")
    @PutMapping("/categories/{id}")
    ResponseEntity<EntityModel<CategoryResponse>> updateCategory(
            @PathVariable UUID id,
            @RequestBody CategoryRequest categoryRequest
    );

    @Operation(summary = "Delete a category")
    @DeleteMapping("/categories/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable UUID id);
}
