package com.bookmyshow.movie_booking_system.dto;

import com.bookmyshow.movie_booking_system.entity.Movie;
import lombok.Getter;

@Getter
public class GetMovieDTO {
    private Long id;
    private final String title;
    private final int duration;
    private final String genre;
    private final double rating;
    private final String posterUrl;
    private final String language;

    public GetMovieDTO(Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.duration = movie.getDuration();
        this.genre = movie.getGenre();
        this.rating = movie.getRating();
        this.posterUrl = movie.getPosterUrl();
        this.language = movie.getLanguage();
    }
}
