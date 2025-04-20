package com.bookmyshow.movie_booking_system.entity.mongodb;

import lombok.Data;

import java.util.List;

@Data
public class SeatRow {
    private String label;
    private List<SeatD> seats;
}
