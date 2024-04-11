package com.hackathone.mts.tripbookingservice.controller;

import com.hackathon.mts.dto.booking.BookingDTO;
import com.hackathone.mts.tripbookingservice.entities.Booking;
import com.hackathone.mts.tripbookingservice.entities.Room;
import com.hackathone.mts.tripbookingservice.service.BookingService;
import com.hackathone.mts.tripbookingservice.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    private final RoomService roomService;
    @Operation(summary = "Create a booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the booking",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing booking request",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingService.addBooking(bookingDTO);
            Room room = booking.getRoom();
            room.setAvailable(false);
            roomService.updateRoom(room);
            bookingDTO.setBookingNumber(booking.getBookingNumber());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok(bookingDTO);
    }

    @Operation(summary = "Get a booking by booking number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the booking",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/{booking_number}")
    public ResponseEntity<?> getBooking(@PathVariable(name = "booking_number") int bookingNumber){
        try {
            BookingDTO bookingDTO = bookingService.getBookingByNumber(bookingNumber);
            return ResponseEntity.ok(bookingDTO);
        } catch (EntityNotFoundException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

