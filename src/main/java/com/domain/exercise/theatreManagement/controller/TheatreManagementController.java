package com.domain.exercise.theatreManagement.controller;

import com.domain.exercise.theatreManagement.entity.City;
import com.domain.exercise.theatreManagement.entity.Screen;
import com.domain.exercise.theatreManagement.entity.Show;
import com.domain.exercise.theatreManagement.entity.Theatre;
import com.domain.exercise.theatreManagement.service.CityService;
import com.domain.exercise.theatreManagement.service.ScreenService;
import com.domain.exercise.theatreManagement.service.ShowService;
import com.domain.exercise.theatreManagement.service.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
    Controller class to add and manage Theatre - (only for Admins)
  */
@RestController
@RequestMapping("v1/api/admin")
public class TheatreManagementController {

    private final CityService cityService;
    private final TheatreService theatreService;
    private final ScreenService screenService;
    private final ShowService showService;

    public TheatreManagementController(CityService cityService, TheatreService theatreService, ScreenService screenService, ShowService showService) {
        this.cityService = cityService;
        this.theatreService = theatreService;
        this.screenService = screenService;
        this.showService = showService;
    }

    /**
    This method will add City
     */
    @PostMapping("/cities")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        return ResponseEntity.ok(cityService.addCity(city));
    }


    /**
     This method will Add Theatre under City
     */
    @PostMapping("/cities/{cityId}/theatres")
    public ResponseEntity<Theatre> addTheatre(
            @PathVariable Long cityId,
            @RequestBody Theatre theatre) {

        return ResponseEntity.ok(
                theatreService.addTheatre(cityId, theatre)
        );
    }

    /**
     This method will Add Screen under Theatre
     */
    @PostMapping("/theatres/{theatreId}/screens")
    public ResponseEntity<Screen> addScreen(
            @PathVariable Long theatreId,
            @RequestBody Screen screen) {

        return ResponseEntity.ok(
                screenService.addScreen(theatreId, screen)
        );
    }

    /**
     This method will Add FULL FLOW: Show + Movie + Seats
     */
    @PostMapping("/screens/{screenId}/shows/full")
    public ResponseEntity<Show> createFullShow(
            @PathVariable Long screenId,
            @RequestBody Show show) {

        return ResponseEntity.ok(
                showService.createFullShow(screenId, show)
        );
    }


    /**
     This method will Update Theatres
     */
    @PutMapping("/theatres/{theatreId}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long theatreId,
                                                 @RequestBody Theatre theatre) {
        Theatre updated = theatreService.updateTheatre(theatreId, theatre);
        return ResponseEntity.ok(updated);
    }

    /**
     This method will Update Show
     */
    @PutMapping("/shows/{showId}")
    public ResponseEntity<Show> updateShow(@PathVariable Long showId,
                                           @RequestBody Show show) {
        Show updated = showService.updateShow(showId, show);
        return ResponseEntity.ok(updated);
    }


    /**
     This method will Delete Theatres
     */
    @DeleteMapping("/theatres/{theatreId}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long theatreId) {
        theatreService.deleteTheatre(theatreId);
        return ResponseEntity.ok("Theatre deleted successfully!");
    }

    /**
     This method will Delete Show
     */
    @DeleteMapping("/shows/{showId}")
    public ResponseEntity<String> deleteShow(@PathVariable Long showId) {
        showService.deleteShow(showId);
        return ResponseEntity.ok("Show deleted successfully!");
    }
}
