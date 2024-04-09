package com.hackathone.mts.tripbookingservice.repository;

import com.hackathone.mts.tripbookingservice.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Optional<Flight> findByFlightNumber(int flightNumber);
    Optional<Flight> findById(int id);
}
