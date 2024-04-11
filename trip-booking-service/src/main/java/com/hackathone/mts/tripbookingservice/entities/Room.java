package com.hackathone.mts.tripbookingservice.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "rooms")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "hotel_id")
    private int hotelId;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "price")
    private int price;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Booking booking;

    @Column(name = "available")
    private boolean available;

    public Room() {}
}
