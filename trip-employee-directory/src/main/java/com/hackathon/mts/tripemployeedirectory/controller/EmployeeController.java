package com.hackathon.mts.tripemployeedirectory.controller;

import com.hackathon.mts.tripemployeedirectory.payload.EmployeeInfo;
import com.hackathon.mts.tripemployeedirectory.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{username}")
    public EmployeeInfo getEmployee(@PathVariable("username") String username) {
        return employeeService.getEmployee(username);
    }
}
