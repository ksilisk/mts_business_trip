package com.hackathone.mts.tripbookingservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.mts.dto.booking.RoomDTO;
import com.hackathone.mts.tripbookingservice.entities.Room;
import com.hackathone.mts.tripbookingservice.repository.HotelRepository;
import com.hackathone.mts.tripbookingservice.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final ObjectMapper modelMapper;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = modelMapper.convertValue(room, RoomDTO.class);
        roomDTO.setHotelName(hotelRepository.findById(room.getHotelId()).orElseThrow().getName());
        return roomDTO;
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getAllRoomsByAvailability() {
        return roomRepository.findRoomsByAvailable(true).orElseThrow(() -> new EntityNotFoundException("All rooms occupied")).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Room getRoom(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber).orElseThrow();
    }

    public void updateRoom(Room room){
        roomRepository.save(room);
    }
}
