package com.hackathon.mts.tripresourceserver.service;

import com.hackathon.mts.dto.booking.BookingDTO;
import com.hackathon.mts.dto.resource.ApplicationDTO;
import com.hackathon.mts.tripresourceserver.client.BookingServiceClient;
import com.hackathon.mts.tripresourceserver.client.EmployeeDirectoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final EmployeeDirectoryClient employeeDirectoryClient;
    private final BookingServiceClient bookingServiceClient;
    private final RoomService roomService;

    private BookingDTO createBookingDTO(ApplicationDTO applicationDTO){
        int roomNumber = roomService.getAvailableRoomNumber();
        String hotelName = "Cosmos";
        int flightNumber = 12345;
        String passportData = employeeDirectoryClient.getEmployeePassportData(applicationDTO.getUsername());
        Date startDate = new Date();
        Date endDate = new Date();
        return new BookingDTO(roomNumber, hotelName, flightNumber, passportData, startDate, endDate, 0);
    }

    public BookingDTO doBooking(ApplicationDTO applicationDTO){
        return bookingServiceClient.createBooking(createBookingDTO(applicationDTO));
    }
}
