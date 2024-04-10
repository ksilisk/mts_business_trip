package com.hackathone.mts.tripbookingservice.service;

import com.hackathone.mts.tripbookingservice.dto.HotelDTO;
import com.hackathone.mts.tripbookingservice.entities.Hotel;
import com.hackathone.mts.tripbookingservice.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public HotelDTO toDTO(Hotel hotel) {
        return new HotelDTO().toDTO(hotel);
    }

    public List<HotelDTO> convertToDTOList(List<Hotel> hotels) {
        List<HotelDTO> list = new ArrayList<>();
        for (Hotel hotel : hotels) {
            list.add(toDTO(hotel));
        }
        return list;
    }
}
