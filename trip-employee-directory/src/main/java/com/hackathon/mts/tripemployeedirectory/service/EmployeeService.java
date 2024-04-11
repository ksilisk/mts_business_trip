package com.hackathon.mts.tripemployeedirectory.service;

import com.hackathon.mts.payload.EmployeeInfo;

public interface EmployeeService {
    EmployeeInfo getEmployee(String username);
    String getEmployeePassportData(String username);
}
