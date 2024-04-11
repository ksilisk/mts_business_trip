package com.hackathon.mts.tripemployeedirectory.controller;

import com.hackathon.mts.payload.EmployeeInfo;
import com.hackathon.mts.tripemployeedirectory.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get employee information by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeInfo.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/{username}")
    public EmployeeInfo getEmployee(@PathVariable("username") String username) {
        return employeeService.getEmployee(username);
    }

    @Operation(summary = "Get passport data of an employee by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved passport data",
                    content = {@Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/getPassportData/{username}")
    public String getEmployeePassportData(@PathVariable("username") String username) {
        return employeeService.getEmployeePassportData(username);
    }
}
