package com.example.contract.exceptions;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, UUID id) {
        super(entityName + " not found with ID: " + id);
    }
}

