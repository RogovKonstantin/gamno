package com.example.contract.dtos;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name
) {}
