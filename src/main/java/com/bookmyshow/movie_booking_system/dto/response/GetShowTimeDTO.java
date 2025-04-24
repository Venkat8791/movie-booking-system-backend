package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class GetShowTimeDTO {
    private final long cinemaId;
    private final String cinemaName;
    private final List<GetShowDTO> shows;

    public GetShowTimeDTO(long cinemaId,String cinemaName ,List<GetShowDTO> shows) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.shows = shows;
    }
}
