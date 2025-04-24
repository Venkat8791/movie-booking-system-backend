package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Getter;


import java.util.List;

@Getter
public class GetShowTimesDTO {
    private final long cinemaId;
    private final long movieId;
    private final String showDate;
    private final List<GetShowDTO> shows;

    public GetShowTimesDTO(long cinemaId,long movieId,String showDate, List<GetShowDTO> shows) {
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.showDate = showDate;
        this.shows = shows;
    }
}
