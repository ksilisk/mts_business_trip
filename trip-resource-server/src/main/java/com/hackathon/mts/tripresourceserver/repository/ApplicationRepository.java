package com.hackathon.mts.tripresourceserver.repository;

import com.hackathon.mts.tripresourceserver.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> getApplicationById(int id);
    Optional<List<Application>> getApplicationByUsername(String username);
    Optional<List<Application>> getApplicationByMasterId(int masterId);
}
