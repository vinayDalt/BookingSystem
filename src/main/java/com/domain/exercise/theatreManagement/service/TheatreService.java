package com.domain.exercise.theatreManagement.service;

import com.domain.exercise.theatreManagement.entity.City;
import com.domain.exercise.theatreManagement.entity.Theatre;
import com.domain.exercise.theatreManagement.repository.CityRepository;
import com.domain.exercise.theatreManagement.repository.TheatreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;
    private final CityRepository cityRepository;

    public TheatreService(TheatreRepository theatreRepository, CityRepository cityRepository) {
        this.theatreRepository = theatreRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional
    public Theatre addTheatre(Long cityId, Theatre theatre) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));

        theatre.setCity(city);
        return theatreRepository.save(theatre);
    }

    @Transactional
    public Theatre updateTheatre(Long theatreId, Theatre theatreRequest) {
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        theatre.setName(theatreRequest.getName());
        theatre.setScreens(theatreRequest.getScreens());
        return theatreRepository.save(theatre);
    }

    @Transactional
    public void deleteTheatre(Long theatreId) {
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
        theatreRepository.delete(theatre);
    }
}
