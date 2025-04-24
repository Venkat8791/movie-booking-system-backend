package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.response.GetShowTimeDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetMovieDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetMoviesResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetShowDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.Movie;
import com.bookmyshow.movie_booking_system.entity.mysql.ShowTime;
import com.bookmyshow.movie_booking_system.exception.dto.MovieNotFoundException;
import com.bookmyshow.movie_booking_system.exception.dto.NoMoviesException;
import com.bookmyshow.movie_booking_system.exception.dto.ShowTimesNotFoundException;
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
        if(movies.isEmpty()){
            throw new NoMoviesException("No movies are playing right now!!");
        }
        return new GetMoviesResponseDTO(movies);
    }

    public GetMovieDTO getMovie(long movieId){
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.map(GetMovieDTO::new).orElseThrow(()-> new MovieNotFoundException("oops, The movie you are searching for is not found"));

    }

    public List<GetShowTimeDTO> getShowTimesByMovieAndDate(long movieId, String dateFromRequest) {
        LocalDate date = LocalDate.parse(dateFromRequest);
        List<ShowTime> showTimes = showTimeRepository.fetchShowTimesForMovie(movieId,date);
        Map<Long,String> cinemaMap = new HashMap<>();

        Map<Long, List<GetShowDTO>> cinemaShowMap = new HashMap<>();
        for(ShowTime showTime: showTimes){
            Long showTimeId = showTime.getId();
            Long screenId = showTime.getScreen().getId();
            String cinemaName = showTime.getScreen().getCinema().getName();
            long cinemaID = showTime.getScreen().getCinema().getId();
            String screenName = showTime.getScreen().getScreenName();
            String showTimeName = showTime.getStartTime().toString();
            int noOfSeats = showTime.getScreen().getTotalSeats();
            int availableSeats = showTime.getAvailableSeats();
            String language = showTime.getLanguage()!=null ? showTime.getLanguage().getLanguageName() : "";
            if(!cinemaMap.containsKey(cinemaID)){
                cinemaMap.put(cinemaID,cinemaName);
            }
            GetShowDTO getShowDTO = new GetShowDTO(showTimeId,screenId,screenName,showTimeName,language,noOfSeats,availableSeats);
            cinemaShowMap.computeIfAbsent(cinemaID,k->new ArrayList<>()).add(getShowDTO);
        }
        List<GetShowTimeDTO> showTimesDTOs =  cinemaShowMap.entrySet().stream().map(entry-> new GetShowTimeDTO(entry.getKey(), cinemaMap.get(entry.getKey()),entry
                .getValue())).collect(Collectors.toList());

        if(showTimesDTOs.isEmpty()){
            throw new ShowTimesNotFoundException("There are no show times available for this movie");
        }
        return showTimesDTOs;

    }
}
