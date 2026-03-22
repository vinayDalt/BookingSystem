package com.domain.exercise.booking.controller;

import com.domain.exercise.booking.service.BookingService;
import com.domain.exercise.theatreManagement.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 This Controller will be used to Book Seats for any movie show
 */
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     This method will Fetch All available seats for particular show
     */
    @GetMapping("/show/{showId}/available-seats")
    public List<Seat> getAvailableSeats(@PathVariable Long showId) {
        return bookingService.getAvailableSeats(showId);
    }

    /**
     This method will book seats for any show
     */
    @PostMapping("/show/{showId}/book")
    public String bookSeats(@PathVariable Long showId, @RequestBody List<Long> seatIds) {
        return bookingService.bookSeats(showId, seatIds);
    }
}
