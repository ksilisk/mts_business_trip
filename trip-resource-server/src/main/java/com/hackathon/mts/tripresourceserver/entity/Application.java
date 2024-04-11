package com.hackathon.mts.tripresourceserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
public class Application {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trip_goal")
    private String tripGoal;

    @Column(name = "username")
    private String username;

    @Column(name = "income_city")
    private String incomeCity;

    @Column(name = "income_country")
    private String incomeCountry;

    @Column(name = "trip_argument")
    private String tripArgument;

    @Column(name = "status")
    private String status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "prepayment_type")
    private String prepaymentType;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "master_username")
    private String masterUsername;
}
