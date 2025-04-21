package com.bookmyshow.movie_booking_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatSectionDTO {
    private String sectionName;
    private int price;
    private List<SeatRowDTO> seatRows;
}
