package com.example.contract.dtos;

import java.math.BigDecimal;
import java.util.UUID;


public record ListingResponse(
        UUID id,
        String title,
        String description,
        BigDecimal price,
        String location,
        String status,
        UUID categoryId,
        UUID userId
) {}

