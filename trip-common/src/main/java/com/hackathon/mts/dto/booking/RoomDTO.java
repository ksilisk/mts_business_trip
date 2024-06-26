package com.hackathon.mts.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomDTO {
    private int roomNumber;
    private int price;
    private String hotelName;
    private boolean available;
}
