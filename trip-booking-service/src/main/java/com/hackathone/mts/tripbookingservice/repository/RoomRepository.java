package com.hackathone.mts.tripbookingservice.repository;

import com.hackathone.mts.tripbookingservice.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByRoomNumber(int roomNumber);
}
