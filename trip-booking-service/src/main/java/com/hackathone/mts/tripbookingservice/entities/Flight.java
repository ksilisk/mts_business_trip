package com.hackathone.mts.tripbookingservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "flight_number")
    private int flightNumber;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @Column(name = "price")
    private int price;

    @Column(name = "airline")
    private String airline;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
