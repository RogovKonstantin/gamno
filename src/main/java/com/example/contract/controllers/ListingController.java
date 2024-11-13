package com.example.contract.controllers;

import com.example.contract.dtos.ListingRequest;
import com.example.contract.dtos.ListingResponse;
import com.example.contract.exceptions.EntityNotFoundException;
import com.example.contract.exceptions.InvalidArgumentException;
import com.example.contract.exceptions.ListingNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;



import java.math.BigDecimal;
@RequestMapping("/listings")
@RestController
public class ListingController implements ListingApi {

    @Override
    public ResponseEntity<ListingResponse> getListingById(UUID id) {
        if (id == null) {
            throw new InvalidArgumentException("Listing ID cannot be null");
        }
        ListingResponse response = findListingById(id);
        if (response == null) {
            throw new ListingNotFoundException(id);
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ListingResponse> createListing(ListingRequest listingRequest) {
        return ResponseEntity.ok(new ListingResponse(
                UUID.randomUUID(),
                listingRequest.title(),
                listingRequest.description(),
                listingRequest.price(),
                listingRequest.location(),
                listingRequest.status(),
                listingRequest.categoryId(),
                listingRequest.userId()
        ));
    }

    @Override
    public ResponseEntity<ListingResponse> patchListing(UUID id, ListingRequest listingRequest) {
        return ResponseEntity.ok(new ListingResponse(
                id,
                listingRequest.title(),
                listingRequest.description(),
                listingRequest.price(),
                listingRequest.location(),
                listingRequest.status(),
                listingRequest.categoryId(),
                listingRequest.userId()
        ));
    }

    @Override
    public ResponseEntity<Void> deleteListing(UUID id) {
        return ResponseEntity.noContent().build();
    }

    private ListingResponse findListingById(UUID id) {
        if (id.equals(UUID.randomUUID())) {
            return new ListingResponse(id, "Title", "Description", BigDecimal.valueOf(100), "Location", "Active", UUID.randomUUID(), UUID.randomUUID());
        }
        return null;
    }
}


