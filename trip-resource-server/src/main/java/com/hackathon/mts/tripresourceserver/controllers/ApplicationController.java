package com.hackathon.mts.tripresourceserver.controllers;


import com.hackathon.mts.dto.resource.ApplicationDTO;
import com.hackathon.mts.tripresourceserver.entity.Application;
import com.hackathon.mts.tripresourceserver.service.ApplicationService;
import com.hackathon.mts.tripresourceserver.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final BookingService bookingService;

    @PostMapping("/applications")
    public List<ApplicationDTO> getApplicationsByStatusAndUsername(
            @RequestParam(name = "status") String status, @RequestParam(name = "username") String username
    ){
        return applicationService.getApplicationsByStatusAnsUsername(status, username);
    }

    @GetMapping("/approve/lead/{id-application}")
    public String changeApplicationStatusOnApprovedByMaster(@PathVariable(name = "id-application") long id){
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("Approved by master");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/approve/accountant/{id-application}")
    public String changeApplicationStatusOnApprovedByAccountant(@PathVariable(name = "id-application") long id){
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("Approved by accountant");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/approve/report-waiting/{id-application}")
    public String changeApplicationStatuReportWaiting(@PathVariable(name = "id-application") long id){
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("Waiting for advance report");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/approve/archived/{id-application}")
    public String changeApplicationStatuArchived(@PathVariable(name = "id-application") long id){
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("Archived");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<?> getApplicationById(@PathVariable(name = "id") long id){
        try{
            ApplicationDTO applicationDTO = applicationService.getApplicationById(id);
            return ResponseEntity.ok(applicationDTO);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/applications/{username}")
    public ResponseEntity<?> getAllApplicationsForUser(@PathVariable(name = "username") String username){
        try{
            List<ApplicationDTO> applicationDTOList = applicationService.getAllApplicationsForUser(username);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/applications/status/{status}")
    public ResponseEntity<?> getAllApplicationsByStatus(@PathVariable(name = "status") String status){
        try{
            List<ApplicationDTO> applicationDTOList = applicationService.getAllApplicationsByStatus(status);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/applications/byMaster/{masterUsername}")
    public ResponseEntity<?> getAllApplicationsByMasterUsername(@PathVariable(name = "masterUsername") String masterUsername){
        try{
            List<ApplicationDTO> applicationDTOList = applicationService.getAllApplicationsByMasterUsername(masterUsername);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/application")
    public ResponseEntity<?> addApplication(@RequestBody ApplicationDTO applicationJson){
        Application application;
        try{
            application = applicationService.saveApplication(applicationJson);
            bookingService.doBooking(applicationJson);
            return ResponseEntity.ok("Application for " + application.getUsername() + " successfully added into DB");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
