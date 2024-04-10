package com.hackathone.mts.tripbookingservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathone.mts.tripbookingservice.dto.BookingDTO;
import com.hackathone.mts.tripbookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final ObjectMapper objectMapper;
    private final BookingService bookingService;

    @Operation(summary = "Create a booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the booking",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing booking request",
                    content = @Content)
    })
    @PostMapping()
    public ResponseEntity<?> createBooking(
            @RequestBody(required = true, content = @Content(schema = @Schema(implementation = BookingDTO.class)))
            String jsonRequest) {
        BookingDTO bookingDTO;
        try {
            bookingDTO = objectMapper.readValue(jsonRequest, BookingDTO.class);
            bookingService.addBooking(bookingDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing booking request");
        }
        return ResponseEntity.ok(bookingDTO);
    }
}
