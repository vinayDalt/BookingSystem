package com.domain.exercise.theatreManagement.service;

import com.domain.exercise.theatreManagement.entity.*;
import com.domain.exercise.theatreManagement.repository.MovieRepository;
import com.domain.exercise.theatreManagement.repository.ScreenRepository;
import com.domain.exercise.theatreManagement.repository.ShowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;

    public ShowService(ShowRepository showRepository, ScreenRepository screenRepository, MovieRepository movieRepository) {
        this.showRepository = showRepository;
        this.screenRepository = screenRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Show createFullShow(Long screenId, Show request) {

        // Screen fetch
        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        // Movie create/fetch
        Movie movie;
        if (request.getMovie().getId() != null) {
            movie = movieRepository.findById(request.getMovie().getId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
        } else {
            movie = movieRepository.save(request.getMovie());
        }

        // Create show
        Show show = new Show();
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        show.setMovie(movie);
        show.setScreen(screen);

        // Derived mapping
        show.setTheatre(screen.getTheatre());
        show.setCity(screen.getTheatre().getCity());

        // Attach seats
        List<Seat> seats = request.getSeats().stream().map(seat -> {
            seat.setShow(show);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            return seat;
        }).toList();

        show.setSeats(seats);

        return showRepository.save(show);
    }

    @Transactional
    public Show updateShow(Long showId, Show showRequest) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        show.setStartTime(showRequest.getStartTime());
        show.setEndTime(showRequest.getEndTime());

        if (showRequest.getMovie() != null) {
            Movie movie;
            if (showRequest.getMovie().getId() != null) {
                movie = movieRepository.findById(showRequest.getMovie().getId())
                        .orElseThrow(() -> new RuntimeException("Movie not found"));
            } else {
                movie = movieRepository.save(showRequest.getMovie());
            }
            show.setMovie(movie);
        }

        return showRepository.save(show);
    }

    @Transactional
    public void deleteShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));
        showRepository.delete(show);
    }
}
