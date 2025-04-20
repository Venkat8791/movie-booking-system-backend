package com.bookmyshow.movie_booking_system.dto;

import com.bookmyshow.movie_booking_system.entity.mysql.LanguageType;
import com.bookmyshow.movie_booking_system.entity.mysql.Movie;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetMovieDTO {
    private Long id;
    private final String title;
    private final int duration;
    private final String genre;
    private final double rating;
    private final String posterUrl;
    private final String description;
    private final List<String> languages;

    public GetMovieDTO(Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.duration = movie.getDuration();
        this.genre = movie.getGenre();
        this.rating = movie.getRating();
        this.posterUrl = movie.getPosterUrl();
        this.description = movie.getDescription();
        this.languages = movie.getLanguages()
                .stream()
                .map(LanguageType::getLanguageName)
                .collect(Collectors.toList());;
    }
}
