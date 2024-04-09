package com.hackathone.mts.tripbookingservice.repository;


import com.hackathone.mts.tripbookingservice.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Optional<Hotel> findByName(String name);
    Optional<Hotel> findById(int id);
}
