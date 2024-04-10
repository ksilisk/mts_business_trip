package com.hackathone.mts.tripbookingservice.controller;

import com.hackathone.mts.tripbookingservice.dto.FlightDTO;
import com.hackathone.mts.tripbookingservice.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Get all flights for a specific day and month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of flights",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlightDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flights not found",
                    content = @Content)
    })
    @GetMapping()
    public List<FlightDTO> getAllFlights(
            @Parameter(description = "Day of the month (1-31)")
            @RequestParam("day") int day,
            @Parameter(description = "Month of the year (1-12)")
            @RequestParam("month") int month) {
        return flightService.convertToDTOList(flightService.getAllFlights());
    }
}
