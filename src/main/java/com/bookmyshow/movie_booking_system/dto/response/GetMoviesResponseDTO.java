package com.bookmyshow.movie_booking_system.dto;

import com.bookmyshow.movie_booking_system.entity.mysql.Movie;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetMoviesResponseDTO {
    public List<GetMovieDTO> movies;

    public GetMoviesResponseDTO(List<Movie> movies){
        this.movies = movies.stream().map(GetMovieDTO::new).collect(Collectors.toList());
    }

    public List<GetMovieDTO> getMovies(){
        return Collections.unmodifiableList(movies);
    }
}
