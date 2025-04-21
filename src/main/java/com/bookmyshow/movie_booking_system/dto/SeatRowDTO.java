package com.bookmyshow.movie_booking_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatRowDTO {
    private String label;
    private List<String> seats;
}
