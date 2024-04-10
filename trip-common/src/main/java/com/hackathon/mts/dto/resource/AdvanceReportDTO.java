package com.hackathon.mts.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdvanceReportDTO {
    private int applicationId;

    private String description;
}
