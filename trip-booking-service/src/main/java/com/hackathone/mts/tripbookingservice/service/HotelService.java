package com.hackathone.mts.tripbookingservice.service;

import com.hackathon.mts.dto.HotelDTO;
import com.hackathon.mts.dto.RoomDTOForHotel;
import com.hackathone.mts.tripbookingservice.entities.Hotel;
import com.hackathone.mts.tripbookingservice.entities.Room;
import com.hackathone.mts.tripbookingservice.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public List<HotelDTO> convertToDTOList(List<Hotel> hotels) {
        List<HotelDTO> list = new ArrayList<>();
        for (Hotel hotel : hotels) {
            list.add(toDTO(hotel));
        }
        return list;
    }

    public HotelDTO toDTO(Hotel hotel) {
        List<RoomDTOForHotel> roomsDTO = hotel.getRooms().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new HotelDTO(roomsDTO, hotel.getName(), hotel.getCity());
    }

    public RoomDTOForHotel toDTO(Room room) {
        return new RoomDTOForHotel(room.getRoomNumber(), room.getPrice());
    }
}
