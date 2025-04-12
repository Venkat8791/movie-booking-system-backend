package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;


@Getter
public class GetShowDTO {
    private final Long showTimeId;
    private final Long screenId;
    private final String screenName;
    private final String showTimeName;
    private final int numOfSeats;
    private final int availableSeats;

    public GetShowDTO(Long showTimeId, Long screenId, String screenName, String showTimeName, int numOfSeats,int availableSeats) {
        this.showTimeId = showTimeId;
        this.screenId = screenId;
        this.screenName = screenName;
        this.numOfSeats = numOfSeats;
        this.showTimeName= showTimeName;
        this.availableSeats = availableSeats;
    }
}
