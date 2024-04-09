package com.hackathone.mts.tripbookingservice.service;

import com.hackathone.mts.tripbookingservice.dto.RoomDTO;
import com.hackathone.mts.tripbookingservice.entities.Room;
import com.hackathone.mts.tripbookingservice.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    public RoomDTO toDTO(Room room){
        return modelMapper.map(room, RoomDTO.class);
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoom(int roomNumber){
        return roomRepository.findByRoomNumber(roomNumber).orElseThrow();
    }
}
