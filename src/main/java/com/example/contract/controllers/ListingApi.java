package com.example.contract.controllers;

import com.example.contract.dtos.ListingRequest;
import com.example.contract.dtos.ListingResponse;
import com.example.contract.exceptions.ExceptionHandlers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Listings API")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Request processed successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListingResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "404", description = "Listing not found",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandlers.StatusResponse.class)))
})
public interface ListingApi {

    @Operation(summary = "Get a listing by ID")
    @GetMapping("/{id}")
    ResponseEntity<ListingResponse> getListingById(@PathVariable UUID id);

    @Operation(summary = "Create a new listing")
    @PostMapping
    ResponseEntity<ListingResponse> createListing(@Valid @RequestBody ListingRequest listingRequest);

    @Operation(summary = "Update a listing")
    @PatchMapping("/{id}")
    ResponseEntity<ListingResponse> patchListing(@PathVariable UUID id, @Valid @RequestBody ListingRequest listingRequest);

    @Operation(summary = "Delete a listing by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteListing(@PathVariable UUID id);
}
