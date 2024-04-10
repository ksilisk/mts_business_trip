package com.hackathone.mts.tripbookingservice.dto;

import com.hackathone.mts.tripbookingservice.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTOForHotel {
    private int roomNumber;
    private int price;

    public static RoomDTOForHotel toDTO(Room room) {
        return new RoomDTOForHotel(room.getRoomNumber(), room.getPrice());
    }


}
