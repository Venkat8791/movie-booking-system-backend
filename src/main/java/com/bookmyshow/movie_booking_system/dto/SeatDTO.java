package com.bookmyshow.movie_booking_system.dto;

import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import com.bookmyshow.movie_booking_system.enums.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SeatDTO {
    private final long seatId;
    private final String rowNumber;
    private final int columnNumber;
    private final SeatType seatType;
    private final int price;
    private final SeatStatus status;

    public SeatDTO(long seatId, String rowNumber, int columnNumber, SeatType seatType, int price, SeatStatus status) {
        this.columnNumber = columnNumber;
        this.seatId = seatId;
        this.rowNumber = rowNumber;
        this.seatType = seatType;
        this.price = price;
        this.status = status;
    }
}
