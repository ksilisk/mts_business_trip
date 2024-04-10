package com.hackathon.mts.tripresourceserver.controllers;

import com.hackathon.mts.tripresourceserver.client.BookingServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {
    private final BookingServiceClient bookingServiceClient;

}
