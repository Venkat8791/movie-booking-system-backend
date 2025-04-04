package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.GetMoviesResponseDTO;
import com.bookmyshow.movie_booking_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mxmovies/v1")
public class MovieController {

    final MovieService movieService;

    public MovieController(@Autowired MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<GetMoviesResponseDTO> getMovies(){
        GetMoviesResponseDTO movies = movieService.getMovies();
        return ResponseEntity.status(200).body(movies);
    }
}
