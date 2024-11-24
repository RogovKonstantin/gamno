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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

    @Operation(summary = "Get listing by ID")
    @GetMapping("/listings/{id}")
    ResponseEntity<EntityModel<ListingResponse>> getListingById(@PathVariable UUID id);

    @Operation(summary = "Create a new listing")
    @PostMapping("/listings")
    ResponseEntity<EntityModel<ListingResponse>> createListing(@RequestBody @Valid ListingRequest listingRequest);

    @Operation(summary = "Get all listings with pagination")
    @GetMapping("/listings")
    ResponseEntity<PagedModel<EntityModel<ListingResponse>>> getAllListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    );

    @Operation(summary = "Update a listing")
    @PatchMapping("/listings/{id}")
    ResponseEntity<EntityModel<ListingResponse>> patchListing(
            @PathVariable UUID id,
            @RequestBody ListingRequest listingRequest
    );

    @Operation(summary = "Soft delete a listing")
    @DeleteMapping("/listings/{id}")
    ResponseEntity<Void> softDeleteListing(@PathVariable UUID id);
}
