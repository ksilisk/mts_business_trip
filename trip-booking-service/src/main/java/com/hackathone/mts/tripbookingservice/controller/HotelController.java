package com.hackathone.mts.tripbookingservice.controller;

import com.hackathone.mts.tripbookingservice.dto.HotelDTO;
import com.hackathone.mts.tripbookingservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    @GetMapping()
    public List<HotelDTO> getAllHotels(){
        return hotelService.convertToDTOList(hotelService.getAllHotels());
    }
}
