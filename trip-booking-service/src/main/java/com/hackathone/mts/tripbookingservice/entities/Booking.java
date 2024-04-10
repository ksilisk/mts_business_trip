package com.hackathone.mts.tripbookingservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "booking_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime bookingDate;

    @Column(name = "check_in_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInTime;

    @Column(name = "check_out_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutTime;

    @Column(name = "booking_number")
    private int bookingNumber;
}
