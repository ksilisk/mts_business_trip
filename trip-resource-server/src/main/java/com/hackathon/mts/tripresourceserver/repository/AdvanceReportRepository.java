package com.hackathon.mts.tripresourceserver.repository;

import com.hackathon.mts.tripresourceserver.entity.AdvanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvanceReportRepository extends JpaRepository<AdvanceReport, Integer> {
    Optional<AdvanceReport> getAdvanceReportByApplicationId(int applicationId);
    Optional<AdvanceReport> getAdvanceReportById(int id);
}
