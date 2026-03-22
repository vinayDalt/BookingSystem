package com.domain.exercise.theatreManagement.service;

import com.domain.exercise.theatreManagement.entity.Screen;
import com.domain.exercise.theatreManagement.entity.Theatre;
import com.domain.exercise.theatreManagement.repository.ScreenRepository;
import com.domain.exercise.theatreManagement.repository.TheatreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    public ScreenService(ScreenRepository screenRepository, TheatreRepository theatreRepository) {
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
    }

    @Transactional
    public Screen addScreen(Long theatreId, Screen screen) {
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        screen.setTheatre(theatre);
        return screenRepository.save(screen);
    }
}
