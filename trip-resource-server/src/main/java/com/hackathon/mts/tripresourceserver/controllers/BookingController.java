package com.hackathon.mts.tripresourceserver.controllers;

import com.hackathon.mts.tripresourceserver.client.BookingServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingServiceClient bookingServiceClient;


}
