package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;


@Getter
public class GetShowDTO {
    private final String screenName;
    private final String showTimeName;
    private final int numOfSeats;

    public GetShowDTO(String screenName,String showTimeName,int numOfSeats) {
        this.screenName = screenName;
        this.numOfSeats = numOfSeats;
        this.showTimeName= showTimeName;
    }
}
