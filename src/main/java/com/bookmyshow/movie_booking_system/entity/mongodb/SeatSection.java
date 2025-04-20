package com.bookmyshow.movie_booking_system.entity.mongodb;

import lombok.Data;

import java.util.List;

@Data
public class SeatSection {
    private String sectionName;
    private int price;
    private List<SeatRow> rows;
}
