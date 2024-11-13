package com.example.contract.exceptions;

import java.util.UUID;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(UUID id) {
        super("Category", id);
    }
}
