package com.hackathon.mts.tripresourceserver.controllers;


import com.hackathon.mts.dto.resource.AccountantDTO;
import com.hackathon.mts.dto.resource.ApplicationDTO;
import com.hackathon.mts.dto.resource.LeadApproveDTO;
import com.hackathon.mts.payload.EmployeeInfo;
import com.hackathon.mts.tripresourceserver.client.EmployeeDirectoryClient;
import com.hackathon.mts.tripresourceserver.entity.Application;
import com.hackathon.mts.tripresourceserver.service.ApplicationService;
import com.hackathon.mts.tripresourceserver.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get applications by status and username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved applications",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/applications")
    public List<ApplicationDTO> getApplicationsByStatusAndUsername(
            @RequestParam(name = "status") String status, @RequestParam(name = "username") String username) {
        return applicationService.getApplicationsByStatusAnsUsername(status, username);
    }

    @Operation(summary = "Change application status to 'report-waiting'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully changed application status",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/approve/report-waiting/{id-application}")
    public String changeApplicationStatusReportWaiting(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("report-waiting");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @Operation(summary = "Change application status to 'archived'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully changed application status",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/approve/archived/{id-application}")
    public String changeApplicationStatusArchived(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("archived");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @Operation(summary = "Get application by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved application",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/application/{id}")
    public ResponseEntity<?> getApplicationById(@PathVariable(name = "id") long id) {
        try {
            ApplicationDTO applicationDTO = applicationService.getApplicationById(id);
            return ResponseEntity.ok(applicationDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(summary = "Get all applications for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved applications",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/applications/{username}")
    public ResponseEntity<?> getAllApplicationsForUser(@PathVariable(name = "username") String username) {
        try {
            List<ApplicationDTO> applicationDTOList = applicationService.getAllApplicationsForUser(username);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(summary = "Get all applications by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved applications",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/applications/status/{status}")
    public ResponseEntity<?> getAllApplicationsByStatus(@PathVariable(name = "status") String status) {
        try {
            List<ApplicationDTO> applicationDTOList = applicationService.getAllApplicationsByStatus(status);
            return ResponseEntity.ok(applicationDTOList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(summary = "Get all applications by master username and status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved applications",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LeadApproveDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
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

    @Operation(summary = "Add application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @PostMapping("/application")
    public ResponseEntity<?> addApplication(@RequestBody ApplicationDTO applicationJson) {
        Application application;
        try {
            application = applicationService.saveApplication(applicationJson);
            return ResponseEntity.ok("Application for " + application.getUsername() + " successfully added into DB");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // accountant
    @Operation(summary = "Change application status to 'approved by accountant'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully changed application status",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/approve/accountant/{id-application}")
    public String changeApplicationStatusOnApprovedByAccountant(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("approved by accountant");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @Operation(summary = "Get accountant information by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved accountant information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountantDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/accountant/{id}")
    public AccountantDTO getAccountantInfo(@PathVariable("id") int id) {
        return applicationService.getAccountantInfo(id);
    }

    @Operation(summary = "Get information about all accountants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved accountant information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountantDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/accountant")
    public List<AccountantDTO> getAccountantInfos() {
        return applicationService.getAllApplicationsByStatus("approved by master").stream()
                .map(ApplicationDTO::getId)
                .map(id -> applicationService.getAccountantInfo(id.intValue()))
                .collect(Collectors.toList());
    }

    // lead
    @Operation(summary = "Change application status to 'approved by lead'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully changed application status",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/approve/lead/{id-application}")
    public String changeApplicationStatusOnApprovedByMaster(@PathVariable(name = "id-application") long id) {
        Application application = applicationService.getApplicationEntity(id);
        application.setStatus("approved by master");
        applicationService.updateApplication(application);
        return "Status = " + application.getStatus();
    }

    @Operation(summary = "Get lead information by application ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved lead information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LeadApproveDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/lead/{app-id}")
    public LeadApproveDTO getLeadInfoApplication(@PathVariable("app-id") long appId) {
        ApplicationDTO application = applicationService.getApplicationById(appId);
        EmployeeInfo employeeInfo = employeeDirectoryClient.getEmployee(application.getUsername());
        return new LeadApproveDTO(application, employeeInfo);
    }

    @Operation(summary = "Update application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @PatchMapping("/application/{id}")
    public ResponseEntity<?> updateApplication(@RequestBody ApplicationDTO applicationDTO, @PathVariable(name = "id") long id){
        try {
            applicationService.updateApplication(applicationDTO, id);
            return ResponseEntity.ok("Application has updated");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
