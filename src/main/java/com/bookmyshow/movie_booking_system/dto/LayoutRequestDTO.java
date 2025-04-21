package com.bookmyshow.movie_booking_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class LayoutRequestDTO {
    private long screenId;
    private long cinemaId;
    private List<SeatSectionDTO> sections;
}
