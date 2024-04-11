package com.hackathon.mts.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private long id;
    private String tripGoal;

    private String username;

    private String incomeCity;

    private String incomeCountry;

    private String tripArgument;

    private String status;

    private LocalDate startDate;

    private LocalDate endDate;

    private String prepaymentType;

    private Integer prepaymentAmount;

    private String cardNumber;

    private String masterUsername;
}
