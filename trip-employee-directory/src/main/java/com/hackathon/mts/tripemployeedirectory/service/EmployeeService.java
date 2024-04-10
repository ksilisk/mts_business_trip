package com.hackathon.mts.tripemployeedirectory.service;

import com.hackathon.mts.tripemployeedirectory.payload.EmployeeInfo;

public interface EmployeeService {
    EmployeeInfo getEmployee(String username);
}
