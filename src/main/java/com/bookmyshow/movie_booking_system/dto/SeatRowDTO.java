package com.bookmyshow.movie_booking_system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SeatRowDTO {
    @NotNull
    private String label;
    @NotNull
    private List<String> seats;
}
