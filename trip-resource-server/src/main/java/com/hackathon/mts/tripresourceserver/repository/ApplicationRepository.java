package com.hackathon.mts.tripresourceserver.repository;

import com.hackathon.mts.tripresourceserver.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> getApplicationById(long id);
    List<Application> findAllByMasterUsernameAndStatus(String masterUsername, String status);
    Optional<List<Application>> getApplicationByUsername(String username);
    Optional<List<Application>> getApplicationByMasterUsername(String masterUsername);
    Optional<List<Application>> getApplicationByStatus(String status);
    Optional<List<Application>> getApplicationsByStatusAndUsername(String status, String username);
}
