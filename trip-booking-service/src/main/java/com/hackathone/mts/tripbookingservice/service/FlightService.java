package com.hackathone.mts.tripbookingservice.service;

import com.hackathone.mts.tripbookingservice.dto.FlightDTO;
import com.hackathone.mts.tripbookingservice.entities.Flight;
import com.hackathone.mts.tripbookingservice.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final ModelMapper modelMapper;
    private final FlightRepository flightRepository;

    @Transactional(readOnly = true)
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public FlightDTO toDTO(Flight flight){
        return modelMapper.map(flight, FlightDTO.class);
    }

    public List<FlightDTO> convertToDTOList(List<Flight> flights){
        List<FlightDTO> list = new ArrayList<>();
        for (Flight flight : flights){
            list.add(toDTO(flight));
        }
        return list;
    }

    public Flight getFlight(int flightNumber){
        return flightRepository.findByFlightNumber(flightNumber).orElseThrow();
    }
}
