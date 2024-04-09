package com.hackathone.mts.tripbookingservice.service;

import com.hackathone.mts.tripbookingservice.entities.Customer;
import com.hackathone.mts.tripbookingservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getCustomer(String passportData){
        return customerRepository.findByPassportData(passportData).orElseThrow();
    }
}
