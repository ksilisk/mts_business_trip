package com.hackathon.mts.tripresourceserver.service;

import com.hackathon.mts.tripresourceserver.client.BookingServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final BookingServiceClient bookingServiceClient;

    public int getAvailableRoomNumber(){
        return bookingServiceClient.getAvailableRooms().get(0).getRoomNumber();
    }
}
