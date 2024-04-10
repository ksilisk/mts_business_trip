package com.hackathone.mts.tripbookingservice.controller;

import com.hackathone.mts.tripbookingservice.dto.HotelDTO;
import com.hackathone.mts.tripbookingservice.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @Operation(summary = "Get all hotels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of hotels",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HotelDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Hotels not found",
                    content = @Content)
    })
    @GetMapping()
    public List<HotelDTO> getAllHotels() {
        return hotelService.convertToDTOList(hotelService.getAllHotels());
    }
}
