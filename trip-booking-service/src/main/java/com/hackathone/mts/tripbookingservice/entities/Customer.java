package com.hackathone.mts.tripbookingservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_surname")
    private String surname;

    @Column(name = "customer_otchestvo")
    private String otchestvo;

    @Column(name = "passport_data")
    private String passportData;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
