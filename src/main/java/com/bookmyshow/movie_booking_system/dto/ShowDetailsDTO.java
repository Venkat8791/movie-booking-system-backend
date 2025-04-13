package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

@Getter
public class ShowDetailsDTO {

    private final String showTimeName;
    private final String screenName;
    private final String cinemaName;
    private final String location;

    public ShowDetailsDTO(String showTimeName, String screenName, String cinemaName, String location) {
        this.showTimeName = showTimeName;
        this.screenName = screenName;
        this.cinemaName = cinemaName;
        this.location = location;
    }
}
