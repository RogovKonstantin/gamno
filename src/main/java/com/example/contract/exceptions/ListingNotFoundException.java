package com.example.contract.exceptions;

import java.util.UUID;

public class ListingNotFoundException extends EntityNotFoundException {
    public ListingNotFoundException(UUID id) {
        super("Listing", id);
    }
}
