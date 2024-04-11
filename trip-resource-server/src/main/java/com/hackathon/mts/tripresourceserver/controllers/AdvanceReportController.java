package com.hackathon.mts.tripresourceserver.controllers;

import com.hackathon.mts.dto.resource.AdvanceReportDTO;
import com.hackathon.mts.tripresourceserver.service.AdvanceReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class AdvanceReportController {
    private final AdvanceReportService advanceReportService;

    @Operation(summary = "Get advance report by application ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved advance report",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdvanceReportDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Advance report not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping("/{application_id}")
    public ResponseEntity<?> getReportByApplicationId(@PathVariable(name = "application_id") long applicationId){
        try {
            AdvanceReportDTO advanceReportDTO = advanceReportService.getAdvanceReportByApplicationId(applicationId);
            return ResponseEntity.ok(advanceReportDTO);
        }catch (EntityNotFoundException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(summary = "Add advance report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> addReport(@RequestBody AdvanceReportDTO advanceReportDTO){
        try{
            advanceReportService.addAdvanceReport(advanceReportDTO);
            return ResponseEntity.ok("Report for application with" + advanceReportDTO.getApplicationId() + " has created");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
