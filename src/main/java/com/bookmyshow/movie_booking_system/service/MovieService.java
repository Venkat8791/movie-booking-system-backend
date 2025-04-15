package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.*;
import com.bookmyshow.movie_booking_system.dto.GetShowTimeDTO;
import com.bookmyshow.movie_booking_system.entity.Movie;
import com.bookmyshow.movie_booking_system.entity.ShowTime;
import com.bookmyshow.movie_booking_system.repository.MovieRepository;
import com.bookmyshow.movie_booking_system.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowTimeRepository showTimeRepository;

    public GetMoviesResponseDTO getMovies(){
        List<Movie> movies = movieRepository.findAll();
        return new GetMoviesResponseDTO(movies);
    }

    public GetMovieDTO getMovie(long movieId){
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.map(GetMovieDTO::new).orElse(null);

    }

    public List<GetShowTimeDTO> getShowTimesByMovieAndDate(long movieId, String dateFromRequest) {
        LocalDate date = LocalDate.parse(dateFromRequest);
        List<ShowTime> showTimes = showTimeRepository.fetchShowTimesForMovie(movieId,date);

        Map<String, List<GetShowDTO>> cinemaShowMap = new HashMap<>();
        for(ShowTime showTime: showTimes){
            Long showTimeId = showTime.getId();
            Long screenId = showTime.getScreen().getId();
            String cinemaName = showTime.getScreen().getCinema().getName();
            long cinemaID = showTime.getScreen().getCinema().getId();
            String screenName = showTime.getScreen().getScreenName();
            String showTimeName = showTime.getStartTime().toString();
            int noOfSeats = showTime.getScreen().getTotalSeats();
            int availableSeats = showTime.getAvailableSeats();
            GetShowDTO getShowDTO = new GetShowDTO(showTimeId,screenId,screenName,showTimeName,noOfSeats,availableSeats);
            cinemaShowMap.computeIfAbsent(cinemaName,k->new ArrayList<>()).add(getShowDTO);
        }
        return cinemaShowMap.entrySet().stream().map(entry-> new GetShowTimeDTO(entry.getKey(), entry
                .getValue())).collect(Collectors.toList());
    }
}
