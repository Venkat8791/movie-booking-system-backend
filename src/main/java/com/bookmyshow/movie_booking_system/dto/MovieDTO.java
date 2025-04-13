package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

@Getter
public class MovieDTO {

    private final String title;
    private final int duration;
    private final String genre;
    private final String posterUrl;
    private final String language;

    public MovieDTO(String title, int duration, String genre, String posterUrl, String language) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.posterUrl = posterUrl;
        this.language = language;
    }
}
