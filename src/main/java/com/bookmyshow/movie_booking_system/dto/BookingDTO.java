package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookingDTO {
    private final long userId;
    private final long showTimeId;
    private final List<Long> seatIds;
    private final double totalPrice;

    public BookingDTO(List<Long> seatIds, long showTimeId, double totalPrice, long userId) {
        this.seatIds = seatIds;
        this.showTimeId = showTimeId;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }
}
