package com.hackathon.mts.tripresourceserver.client;

import com.hackathon.mts.dto.booking.BookingDTO;
import com.hackathon.mts.dto.booking.FlightDTO;
import com.hackathon.mts.dto.booking.HotelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "bookingServiceClient", url = "${trip.url.booking-service}")
public interface BookingServiceClient {
    @PostMapping("/api/booking")
    BookingDTO createBooking(@RequestBody BookingDTO bookingDTO);

    @GetMapping("/api/flight")
    List<FlightDTO> getAllFights(@RequestParam("day") int day, @RequestParam("month") int month);

    @GetMapping("/api/hotels")
    List<HotelDTO> getAllHotels();
}
