package com.domain.exercise.booking.controller;

import com.domain.exercise.booking.service.BookingService;
import com.domain.exercise.theatreManagement.entity.Seat;
import com.domain.exercise.theatreManagement.entity.SeatStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    void testGetAvailableSeats() throws Exception {
        Seat seat = new Seat();
        seat.setId(1L);
        seat.setSeatStatus(SeatStatus.AVAILABLE);

        when(bookingService.getAvailableSeats(1L)).thenReturn(List.of(seat));

        mockMvc.perform(get("/api/booking/show/1/available-seats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].seatStatus").value("AVAILABLE"));
    }

    @Test
    void testBookSeats() throws Exception {
        List<Long> seatIds = List.of(1L, 2L);

        when(bookingService.bookSeats(1L, seatIds)).thenReturn("Seats booked successfully!");

        mockMvc.perform(post("/api/booking/show/1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1,2]"))
                .andExpect(status().isOk())
                .andExpect(content().string("Seats booked successfully!"));
    }
}