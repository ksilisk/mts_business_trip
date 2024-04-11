package com.hackathone.mts.tripbookingservice.controller;

import com.hackathon.mts.dto.booking.RoomDTO;
import com.hackathone.mts.tripbookingservice.service.RoomService;
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

    @GetMapping
    public List<RoomDTO> getFreeRooms(){
        return roomService.getAllRoomsByAvailability();
    }
}
