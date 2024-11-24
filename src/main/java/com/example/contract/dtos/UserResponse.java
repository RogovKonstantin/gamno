package com.example.contract.dtos;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String email,
        List<UUID> listingIds
) {}
