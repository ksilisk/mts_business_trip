package com.hackathon.mts.tripemployeedirectory.service.impl;

import com.hackathon.mts.tripemployeedirectory.entity.Employee;
import com.hackathon.mts.tripemployeedirectory.exception.EntityNotFoundException;
import com.hackathon.mts.payload.EmployeeInfo;
import com.hackathon.mts.tripemployeedirectory.repository.EmployeeRepository;
import com.hackathon.mts.tripemployeedirectory.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeInfo getEmployee(String username) {
        Employee employee = employeeRepository.findEmployeeByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return createInfo(employee);
    }

    private EmployeeInfo createInfo(Employee employee) {
        return new EmployeeInfo(employee.getName(), employee.getSurname(), employee.getPatronymic(),
                employee.getPhone(), employee.getEmail(), employee.getPosition(), employee.getRole().getRole(),
                employeeRepository.findById(employee.getMasterId()).orElseThrow(() -> new EntityNotFoundException("Entity not found")).getUsername());
    }
}
