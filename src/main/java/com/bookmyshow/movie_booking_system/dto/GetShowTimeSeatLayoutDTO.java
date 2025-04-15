package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GetShowTimeSeatLayoutDTO {
    private final Long showTimeId;
    private final String showTimeName;
    private final String screenName;
    private final String cinemaName;
    private final String movieName;
    private final String showDate;
    private final List<SeatDTO> seats;

    public GetShowTimeSeatLayoutDTO(Long showTimeId, String cinemaName, String screenName, String showTimeName, String movieName,String showDate, List<SeatDTO> seats) {
        this.cinemaName = cinemaName;
        this.movieName = movieName;
        this.screenName = screenName;
        this.seats = seats;
        this.showTimeId = showTimeId;
        this.showDate = showDate;
        this.showTimeName = showTimeName;
    }
}
