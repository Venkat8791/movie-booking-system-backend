package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

@Getter
public class MovieDTO {

    private final String title;
    private final int duration;
    private final String genre;
    private final String posterUrl;


    public MovieDTO(String title, int duration, String genre, String posterUrl) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.posterUrl = posterUrl;
    }
}
