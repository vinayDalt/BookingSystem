package com.domain.exercise.booking.service;

import com.domain.exercise.theatreManagement.entity.Seat;
import com.domain.exercise.theatreManagement.entity.SeatStatus;
import com.domain.exercise.theatreManagement.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailableSeats() {
        Seat seat1 = new Seat();
        seat1.setId(1L);
        seat1.setSeatStatus(SeatStatus.AVAILABLE);

        Seat seat2 = new Seat();
        seat2.setId(2L);
        seat2.setSeatStatus(SeatStatus.BOOKED);

        when(seatRepository.findByShowIdAndSeatStatus(1L, SeatStatus.AVAILABLE))
                .thenReturn(List.of(seat1));

        List<Seat> availableSeats = bookingService.getAvailableSeats(1L);

        assertEquals(1, availableSeats.size());
        assertEquals(SeatStatus.AVAILABLE, availableSeats.get(0).getSeatStatus());
    }

    @Test
    void testBookSeatsSuccess() {
        Seat seat1 = new Seat();
        seat1.setId(1L);
        seat1.setSeatStatus(SeatStatus.AVAILABLE); // <- important, use field

        when(seatRepository.findById(1L)).thenReturn(Optional.of(seat1));
        when(seatRepository.saveAndFlush(any(Seat.class))).thenReturn(seat1);

        String result = bookingService.bookSeats(1L, List.of(1L));

        assertEquals("Seats booked successfully!", result);
        verify(seatRepository, times(1)).saveAndFlush(seat1);
        assertEquals(SeatStatus.BOOKED, seat1.getSeatStatus()); // ensure seat is updated
    }

    @Test
    void testBookSeatsAlreadyBooked() {
        Seat seat1 = new Seat();
        seat1.setId(1L);
        seat1.setSeatStatus(SeatStatus.BOOKED); // <- field represents booked seat

        when(seatRepository.findById(1L)).thenReturn(Optional.of(seat1));

        String result = bookingService.bookSeats(1L, List.of(1L));

        assertEquals("Seat 1 is already booked", result);
        verify(seatRepository, never()).saveAndFlush(any());
    }
}