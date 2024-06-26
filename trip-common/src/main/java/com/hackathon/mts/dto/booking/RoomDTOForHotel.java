package com.hackathon.mts.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTOForHotel {
    private int roomNumber;
    private int price;
    private boolean available;
}
