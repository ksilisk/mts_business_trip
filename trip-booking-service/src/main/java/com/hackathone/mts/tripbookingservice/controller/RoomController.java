package com.hackathone.mts.tripbookingservice.controller;

import com.hackathon.mts.dto.booking.RoomDTO;
import com.hackathone.mts.tripbookingservice.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @Operation(summary = "Get all free rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of free rooms",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping
    public List<RoomDTO> getFreeRooms(){
        return roomService.getAllRoomsByAvailability();
    }
}
