package com.hackathon.mts.dto.resource;

import com.hackathon.mts.payload.EmployeeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountantDTO {
    private EmployeeInfo lead;
    private EmployeeInfo employee;
    private ApplicationDTO application;
}
