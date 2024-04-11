package com.hackathon.mts.tripresourceserver.controllers;

import com.hackathon.mts.dto.resource.AdvanceReportDTO;
import com.hackathon.mts.tripresourceserver.service.AdvanceReportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class AdvanceReportController {
    private final AdvanceReportService advanceReportService;
    @GetMapping("/{application_id}")
    public ResponseEntity<?> getReportByApplicationId(@PathVariable(name = "application_id") long applicationId){
        try {
            AdvanceReportDTO advanceReportDTO = advanceReportService.getAdvanceReportByApplicationId(applicationId);
            return ResponseEntity.ok(advanceReportDTO);
        }catch (EntityNotFoundException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

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
