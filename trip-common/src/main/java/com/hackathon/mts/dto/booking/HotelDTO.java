package com.hackathon.mts.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private List<RoomDTOForHotel> roomsDTO;
    private String name;
    private String city;
}
