package com.hackathone.mts.tripbookingservice.dto;

import com.hackathone.mts.tripbookingservice.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private List<RoomDTOForHotel> roomsDTO;
    private String name;
    private String city;

    public HotelDTO toDTO(Hotel hotel) {
        List<RoomDTOForHotel> roomsDTO = hotel.getRooms().stream()
                .map(RoomDTOForHotel::toDTO)
                .collect(Collectors.toList());
        return new HotelDTO(roomsDTO, hotel.getName(), hotel.getCity());
    }
}
