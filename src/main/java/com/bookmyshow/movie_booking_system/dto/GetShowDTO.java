package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;


@Getter
public class GetShowDTO {
    private final Long showTimeId;
    private final String screenName;
    private final String showTimeName;
    private final int numOfSeats;

    public GetShowDTO(Long showTimeId,String screenName,String showTimeName,int numOfSeats) {
        this.showTimeId = showTimeId;
        this.screenName = screenName;
        this.numOfSeats = numOfSeats;
        this.showTimeName= showTimeName;
    }
}
