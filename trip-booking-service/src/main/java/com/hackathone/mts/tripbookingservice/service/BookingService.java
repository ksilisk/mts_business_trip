package com.hackathone.mts.tripbookingservice.service;

import com.hackathon.mts.dto.BookingDTO;
import com.hackathone.mts.tripbookingservice.entities.Booking;
import com.hackathone.mts.tripbookingservice.entities.Customer;
import com.hackathone.mts.tripbookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerService customerService;
    private final RoomService roomService;
    private final FlightService flightService;

    public List<Booking> getCustomersBooking(Customer customer){
        return bookingRepository.findByCustomer(customer).orElseThrow();
    }

    @Transactional()
    public void addBooking(BookingDTO bookingDTO){
        bookingRepository.save(convertToBooking(bookingDTO));
    }

    public Booking convertToBooking(BookingDTO bookingDTO){
        Booking booking = new Booking();
        enrichBooking(booking, bookingDTO);
        return booking;
    }

    public void enrichBooking(Booking booking, BookingDTO bookingDTO){
        booking.setCustomer(customerService.getCustomer(bookingDTO.getPassportData()));
        booking.setRoom(roomService.getRoom(bookingDTO.getRoomNumber()));
        booking.setFlight(flightService.getFlight(bookingDTO.getFlightNumber()));
        booking.setCheckInTime(bookingDTO.getCheckInTime());
        booking.setCheckOutTime(bookingDTO.getCheckOutTime());
        booking.setBookingDate(LocalDateTime.now(ZoneId.of("Europe/Moscow")));
    }
}
