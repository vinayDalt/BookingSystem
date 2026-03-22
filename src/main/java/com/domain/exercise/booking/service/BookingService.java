package com.domain.exercise.booking.service;

import com.domain.exercise.theatreManagement.entity.Seat;
import com.domain.exercise.theatreManagement.entity.SeatStatus;
import com.domain.exercise.theatreManagement.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookingService {

    private final SeatRepository seatRepository;
    private final Map<Long, ReentrantLock> showLocks = new ConcurrentHashMap<>();

    @Autowired
    public BookingService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAvailableSeats(Long showId) {
        return seatRepository.findByShowIdAndSeatStatus(showId, SeatStatus.AVAILABLE);
    }

    public String bookSeats(Long showId, List<Long> seatIds) {
        ReentrantLock lock = showLocks.computeIfAbsent(showId, id -> new ReentrantLock());

        lock.lock();
        try {
            for (Long seatId : seatIds) {
                // Fetch fresh seat status from DB
                Seat seat = seatRepository.findById(seatId)
                        .orElseThrow(() -> new RuntimeException("Seat not found: " + seatId));

                // Always check DB status
                if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
                    return "Seat " + seatId + " is already booked";
                }

                seat.book();  // sets status to BOOKED
                seatRepository.saveAndFlush(seat);  // immediately flush to DB
            }
        } finally {
            lock.unlock();
        }
        return "Seats booked successfully!";
    }
}
