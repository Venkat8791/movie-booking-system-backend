package com.bookmyshow.movie_booking_system.dto;

import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import com.bookmyshow.movie_booking_system.enums.SeatType;
import lombok.Getter;

@Getter
public class SeatDTO {
    private final String seatId;
    private final String rowNumber;
    private final int columnNumber;
    private final SeatType seatType;
    private final SeatStatus status;

    public SeatDTO(String seatId, String rowNumber, int columnNumber, SeatType seatType, SeatStatus status) {
        this.columnNumber = columnNumber;
        this.seatId = seatId;
        this.rowNumber = rowNumber;
        this.seatType = seatType;
        this.status = status;
    }
}
