package com.hackathone.mts.tripbookingservice.repository;

import com.hackathone.mts.tripbookingservice.entities.Booking;
import com.hackathone.mts.tripbookingservice.entities.Customer;
import com.hackathone.mts.tripbookingservice.entities.Flight;
import com.hackathone.mts.tripbookingservice.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<List<Booking>> findByCustomer(Customer customer);
    Optional<List<Booking>> findByFlight(Flight flight);
    Optional<Booking> findByRoom(Room room);
    Optional<Booking> findById(int id);
    Optional<Booking> findByBookingNumber(int number);
}
