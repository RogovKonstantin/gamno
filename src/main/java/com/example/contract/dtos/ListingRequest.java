package com.example.contract.dtos;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record ListingRequest(
        @NotNull(message = "Title is required") String title,
        String description,
        @NotNull(message = "Price is required") BigDecimal price,
        String location,
        String status,
        UUID categoryId,
        UUID userId
) {}
