package com.hackathon.mts.tripresourceserver.controllers;

import com.hackathon.mts.payload.EmployeeInfo;
import com.hackathon.mts.tripresourceserver.client.EmployeeDirectoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeDirectoryClient employeeDirectoryClient;

    @GetMapping("/employee/{username}")
    public EmployeeInfo getEmployee(@PathVariable("username") String username) {
        return employeeDirectoryClient.getEmployee(username);
    }

}
