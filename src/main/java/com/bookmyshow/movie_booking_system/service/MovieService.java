package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.GetMovieDTO;
import com.bookmyshow.movie_booking_system.dto.GetMoviesResponseDTO;
import com.bookmyshow.movie_booking_system.entity.Movie;
import com.bookmyshow.movie_booking_system.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public GetMoviesResponseDTO getMovies(){
        List<Movie> movies = movieRepository.findAll();
        return new GetMoviesResponseDTO(movies);

    }
}
