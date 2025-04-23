package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GetMovieShowTimesDTO {
    private final String showDate;
    private final List<GetShowTimeDTO> showTimes;

    public GetMovieShowTimesDTO(String showDate, List<GetShowTimeDTO> showTimes) {
        this.showDate = showDate;
        this.showTimes = showTimes;
    }
}
