package com.hackathone.mts.tripbookingservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathone.mts.tripbookingservice.dto.BookingDTO;
import com.hackathone.mts.tripbookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final ObjectMapper objectMapper;
    private final BookingService bookingService;
    @PostMapping()
    public ResponseEntity<?> createBooking(@RequestBody String jsonRequest){
        BookingDTO bookingDTO;
        try {
            bookingDTO = objectMapper.readValue(jsonRequest, BookingDTO.class);
            bookingService.addBooking(bookingDTO);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing booking request");

        }
        return ResponseEntity.ok(bookingDTO);
    }
}
