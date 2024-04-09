package com.hackathone.mts.tripbookingservice.repository;

import com.hackathone.mts.tripbookingservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByPassportData(String passportData);
    Optional<Customer> findById(int id);
}
