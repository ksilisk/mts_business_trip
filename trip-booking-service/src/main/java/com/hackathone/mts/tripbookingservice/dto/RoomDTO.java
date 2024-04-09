package com.hackathone.mts.tripbookingservice.dto;

import com.hackathone.mts.tripbookingservice.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomDTO {
    private int roomNumber;
    private int price;
    private HotelDTOForRoom hotelDTOForRoom;
}
