package com.hackathon.mts.tripresourceserver.controllers;


import com.hackathon.mts.dto.resource.AccountantDTO;
import com.hackathon.mts.dto.resource.ApplicationDTO;
import com.hackathon.mts.dto.resource.LeadApproveDTO;
import com.hackathon.mts.payload.EmployeeInfo;
import com.hackathon.mts.tripresourceserver.client.EmployeeDirectoryClient;
import com.hackathon.mts.tripresourceserver.entity.Application;
import com.hackathon.mts.tripresourceserver.service.ApplicationService;
import com.hackathon.mts.tripresourceserver.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final BookingService bookingService;
    private final EmployeeDirectoryClient employeeDirectoryClient;

    @PostMapping("/applications")
    public List<ApplicationDTO> getApplicationsByStatusAndUsername(
            @RequestParam(name = "status") String status, @RequestParam(name = "username") String username) {
        return applicationService.getApplicationsByStatusAnsUsername(status, username);
    }

    @GetMapping("/approve/report-waiting/{id-application}")
    public String changeApplicationStatusReportWaiting(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("report-waiting");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/approve/archived/{id-application}")
    public String changeApplicationStatusArchived(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("archived");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<?> getApplicationById(@PathVariable(name = "id") long id) {
        try {
            ApplicationDTO applicationDTO = applicationService.getApplicationById(id);
            return ResponseEntity.ok(applicationDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/applications/{username}")
    public ResponseEntity<?> getAllApplicationsForUser(@PathVariable(name = "username") String username) {
        try {
            List<ApplicationDTO> applicationDTOList = applicationService.getAllApplicationsForUser(username);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/applications/status/{status}")
    public ResponseEntity<?> getAllApplicationsByStatus(@PathVariable(name = "status") String status) {
        try {
            List<ApplicationDTO> applicationDTOList = applicationService.getAllApplicationsByStatus(status);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/applications/master")
    public ResponseEntity<?> getAllApplicationsByMasterUsername(@RequestParam("master") String masterUsername,
                                                                @RequestParam("status") String status) {
        try {
            List<LeadApproveDTO> applicationDTOList = applicationService.getAllApplicationsByMasterUsername(masterUsername, status);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/application")
    public ResponseEntity<?> addApplication(@RequestBody ApplicationDTO applicationJson) {
        Application application;
        try {
            application = applicationService.saveApplication(applicationJson);
            bookingService.doBooking(applicationJson);
            return ResponseEntity.ok("Application for " + application.getUsername() + " successfully added into DB");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // accountant
    @GetMapping("/approve/accountant/{id-application}")
    public String changeApplicationStatusOnApprovedByAccountant(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("approved by accountant");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/accountant/{id}")
    public AccountantDTO getAccountantInfo(@PathVariable("id") int id) {
        return applicationService.getAccountantInfo(id);
    }

    @GetMapping("/accountant")
    public List<AccountantDTO> getAccountantInfos() {
        return applicationService.getAllApplicationsByStatus("approved by master").stream()
                .map(ApplicationDTO::getId)
                .map(id -> applicationService.getAccountantInfo(id.intValue()))
                .collect(Collectors.toList());
    }

    // lead

    @GetMapping("/approve/lead/{id-application}")
    public String changeApplicationStatusOnApprovedByMaster(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("approved by master");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/lead/{app-id}")
    public LeadApproveDTO getLeadInfoApplication(@PathVariable("app-id") long appId) {
        ApplicationDTO application = applicationService.getApplicationById(appId);
        EmployeeInfo employeeInfo = employeeDirectoryClient.getEmployee(application.getUsername());
        return new LeadApproveDTO(application, employeeInfo);
    }
}
