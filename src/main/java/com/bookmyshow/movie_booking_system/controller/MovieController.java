package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.response.GetMovieDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetMovieShowTimesDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetMoviesResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetShowTimeDTO;
import com.bookmyshow.movie_booking_system.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mxmovies/v1")
public class MovieController {

    final MovieService movieService;

    public MovieController(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<GetMoviesResponseDTO> getMovies() {
        log.info("**** FETCHING ALL MOVIES ****");
        GetMoviesResponseDTO movies = movieService.getMovies();
        log.info("**** ALL MOVIES FETCHED ****");
        return ResponseEntity.status(200).body(movies);
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<GetMovieDTO> getMovie(@PathVariable long movieId) {
        log.info("**** FETCHING MOVIE:{} ****", movieId);
        GetMovieDTO movie = movieService.getMovie(movieId);
        log.info("**** EXITING FETCH MOVIE ****");
        return ResponseEntity.status(200).body(movie);
    }

    @GetMapping("/movies/{movieId}/showtimes")
    public ResponseEntity<GetMovieShowTimesDTO> getMovie(@PathVariable long movieId, @RequestParam(required = false) String showdate) {
        log.info("**** FETCHING SHOW TIMES FOR MOVIE: {}, SHOW DATE:{} ****", movieId, showdate);
        if (showdate == null || showdate.isEmpty()) {
            showdate = LocalDate.now().toString();
        }
        List<GetShowTimeDTO> showTimesForMovie = movieService.getShowTimesByMovieAndDate(movieId, showdate);
        GetMovieShowTimesDTO getMovieShowTimesDTO = new GetMovieShowTimesDTO(showdate, showTimesForMovie);
        log.info("**** FETCHED SHOW TIMES FOR MOVIE: {}, SHOW DATE:{} ****", movieId, showdate);
        return ResponseEntity.status(200).body(getMovieShowTimesDTO);
    }
}
