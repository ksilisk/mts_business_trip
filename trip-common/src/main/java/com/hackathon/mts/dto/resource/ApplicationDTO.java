package com.hackathon.mts.dto.resource;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ApplicationDTO {
    private String tripGoal;

    private String username;

    private String incomeCity;

    private String incomeCountry;

    private String tripArgument;

    private String status;

    private Date startDate;

    private Date endDate;

    private String prepaymentType;

    private String cardNumber;

    private int masterId;
}
