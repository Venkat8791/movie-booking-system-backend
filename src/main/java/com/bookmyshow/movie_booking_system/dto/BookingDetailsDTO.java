package com.bookmyshow.movie_booking_system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class BookingDetailsDTO {
    private final long bookingId;
    private final MovieDTO movie;
    private final String language;
    private final ShowDetailsDTO showDetails;
    private final List<String> seatNumbers;
    private final double totalPrice;

    public BookingDetailsDTO(long bookingId,String language, MovieDTO movie, ShowDetailsDTO showDetails, List<String> seatNumbers, double totalPrice ) {
        this.bookingId = bookingId;
        this.movie = movie;
        this.showDetails = showDetails;
        this.seatNumbers = seatNumbers;
        this.totalPrice = totalPrice;
        this.language = language;
    }
}
