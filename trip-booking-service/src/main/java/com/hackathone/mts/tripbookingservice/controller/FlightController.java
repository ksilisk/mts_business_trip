package com.hackathone.mts.tripbookingservice.controller;

import com.hackathone.mts.tripbookingservice.dto.FlightDTO;
import com.hackathone.mts.tripbookingservice.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @GetMapping()
    public List<FlightDTO> getAllFlights(@RequestParam("day") int day, @RequestParam("month") int month){
        return flightService.convertToDTOList(flightService.getAllFlights());
    }
}
