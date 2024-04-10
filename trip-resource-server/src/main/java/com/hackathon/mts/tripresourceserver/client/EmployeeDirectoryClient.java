package com.hackathon.mts.tripresourceserver.client;

import com.hackathon.mts.payload.EmployeeInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employeeDirectoryClient", url = "${trip.url.employee-directory}")
public interface EmployeeDirectoryClient {
    @GetMapping("/api/employee/{username}")
    EmployeeInfo getEmployee(@PathVariable("username") String username);
}
