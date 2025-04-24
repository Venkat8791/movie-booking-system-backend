package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Getter;

import java.util.Date;

@Getter
public class BookingResponseDTO {

    private final long bookingId;
    private final long userId;
    private final long showTimeId;
    private final int numOfSeats;
    private final double totalPrice;
    private final Date bookingDate;

    public BookingResponseDTO(long bookingId, long userId, long showTimeId, int numOfSeats, double totalPrice, Date bookingDate) {
        this.bookingDate = bookingDate;
        this.bookingId = bookingId;
        this.numOfSeats = numOfSeats;
        this.showTimeId = showTimeId;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }
}
