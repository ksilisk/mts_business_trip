package com.hackathon.mts.dto.resource;

import com.hackathon.mts.payload.EmployeeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LeadApproveDTO {
    private ApplicationDTO application;
    private EmployeeInfo employee;
}
