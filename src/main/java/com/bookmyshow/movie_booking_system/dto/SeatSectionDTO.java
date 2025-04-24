package com.bookmyshow.movie_booking_system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SeatSectionDTO {
    @NotNull
    private String sectionName;
    @NotNull
    private int price;
    @NotNull
    private List<SeatRowDTO> seatRows;
}
