package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GetShowTimeDTO {
    private final String cinemaName;
    private final List<GetShowDTO> shows;

    public GetShowTimeDTO(String cinemaName ,List<GetShowDTO> shows) {
        this.cinemaName = cinemaName;
        this.shows = shows;
    }
}
