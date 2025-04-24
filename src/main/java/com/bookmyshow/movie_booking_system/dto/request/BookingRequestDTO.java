package com.bookmyshow.movie_booking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class BookingRequestDTO {

    @NotNull
    private final long userId;

    @NotNull
    private final long showTimeId;

    @NotNull
    private final List<String> seatIds;

    @NotNull
    private final double totalPrice;

    public BookingRequestDTO(List<String> seatIds, long showTimeId, double totalPrice, long userId) {
        this.seatIds = seatIds;
        this.showTimeId = showTimeId;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "seatIds=" + seatIds +
                ", userId=" + userId +
                ", showTimeId=" + showTimeId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
