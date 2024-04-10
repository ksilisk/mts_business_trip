package com.hackathon.mts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomDTO {
    private int roomNumber;
    private int price;
    private HotelDTOForRoom hotelDTOForRoom;
}
