package com.bookmyshow.movie_booking_system.entity.mongodb;

import lombok.Data;

@Data
public class SeatD {
    private String seatId;
    private String label;
    private boolean isGap;
}
