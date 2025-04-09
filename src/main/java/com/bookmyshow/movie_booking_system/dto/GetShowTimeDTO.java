package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GetShowTimeDTO {
    private final String cinemaName;
    private final List<GetShowDTO> screens;

    public GetShowTimeDTO(String cinemaName ,List<GetShowDTO> screens) {
        this.cinemaName = cinemaName;
        this.screens = screens;
    }
}
