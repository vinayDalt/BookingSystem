package com.domain.exercise.theatreManagement.repository;



import com.domain.exercise.theatreManagement.entity.Seat;
import com.domain.exercise.theatreManagement.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Find all available seats for a show
    List<Seat> findByShowIdAndSeatStatus(Long showId, SeatStatus seatStatus);
}
