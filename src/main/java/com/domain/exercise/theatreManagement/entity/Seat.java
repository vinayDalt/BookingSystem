package com.domain.exercise.theatreManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private Double basePrice;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    private String description;

    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonIgnore
    private Show show;

    public SeatStatus seatStatus() {
        return SeatStatus.AVAILABLE;
    }

    public void book() {
        if (!seatStatus().equals(SeatStatus.AVAILABLE)) {
            throw new RuntimeException("Seat " + id + " is already booked");
        }
        this.seatStatus = SeatStatus.BOOKED;
    }
}
