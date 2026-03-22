package com.domain.exercise.theatreManagement.service;

import com.domain.exercise.theatreManagement.entity.City;
import com.domain.exercise.theatreManagement.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }
}
