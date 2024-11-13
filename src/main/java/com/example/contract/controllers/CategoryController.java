package com.example.contract.controllers;

import com.example.contract.dtos.CategoryRequest;
import com.example.contract.dtos.CategoryResponse;
import com.example.contract.exceptions.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RequestMapping("/categories")
@RestController
public class CategoryController implements CategoryApi {

    @Override
    public ResponseEntity<CategoryResponse> getCategoryById(UUID id) {
        CategoryResponse category = findCategoryById(id);
        if (category == null) {
            throw new CategoryNotFoundException(id);
        }
        return ResponseEntity.ok(category);
    }

    @Override
    public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        return ResponseEntity.ok(new CategoryResponse(UUID.randomUUID(), categoryRequest.name()));
    }

    @Override
    public ResponseEntity<CategoryResponse> updateCategory(UUID id, CategoryRequest categoryRequest) {
        return ResponseEntity.ok(new CategoryResponse(id, categoryRequest.name()));
    }

    @Override
    public ResponseEntity<Void> deleteCategory(UUID id) {
        return ResponseEntity.noContent().build();
    }

    private CategoryResponse findCategoryById(UUID id) {
        return new CategoryResponse(id, "Sample Category");
    }
}
