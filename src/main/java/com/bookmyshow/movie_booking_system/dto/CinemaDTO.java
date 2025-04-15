package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

@Getter
public class CinemaDTO {
    private final long cinemaId;
    private final String cinemaName;

    public CinemaDTO(long cinemaId, String cinemaName) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
    }
}
