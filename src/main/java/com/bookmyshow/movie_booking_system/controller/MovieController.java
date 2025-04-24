package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.response.GetMovieDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetMovieShowTimesDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetMoviesResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetShowTimeDTO;
import com.bookmyshow.movie_booking_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


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

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<GetMovieDTO> getMovie(@PathVariable long movieId){
        GetMovieDTO movie = movieService.getMovie(movieId);
        return ResponseEntity.status(200).body(movie);
    }

    @GetMapping("/movies/{movieId}/showtimes")
    public ResponseEntity<GetMovieShowTimesDTO> getMovie(@PathVariable long movieId, @RequestParam(required = false) String showdate){
        if(showdate == null || showdate.isEmpty()){
            showdate = LocalDate.now().toString();
        }
        List<GetShowTimeDTO> showTimesForMovie = movieService.getShowTimesByMovieAndDate(movieId,showdate);
        GetMovieShowTimesDTO getMovieShowTimesDTO = new GetMovieShowTimesDTO(showdate,showTimesForMovie);
        return ResponseEntity.status(200).body(getMovieShowTimesDTO);
    }
}
