package com.hackathon.mts.tripresourceserver.service;

import com.hackathon.mts.dto.resource.AdvanceReportDTO;
import com.hackathon.mts.tripresourceserver.entity.AdvanceReport;
import com.hackathon.mts.tripresourceserver.repository.AdvanceReportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvanceReportService {
    private final AdvanceReportRepository advanceReportRepository;
    private final ModelMapper modelMapper;

    public AdvanceReportDTO getAdvanceReportByApplicationId(long applicationId){
        return advanceReportRepository.getAdvanceReportByApplicationId(applicationId).map(this::toDTO).orElseThrow();
    }

    private AdvanceReportDTO toDTO(AdvanceReport advanceReport){
        return modelMapper.map(advanceReport, AdvanceReportDTO.class);
    }

    private AdvanceReport toAdvanceReport(AdvanceReportDTO advanceReportDTO){
        return modelMapper.map(advanceReportDTO, AdvanceReport.class);
    }

    public void addAdvanceReport(AdvanceReportDTO advanceReportDTO){
        advanceReportRepository.save(toAdvanceReport(advanceReportDTO));
    }
}
