package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.GetShowTimeSeatLayoutDTO;
import com.bookmyshow.movie_booking_system.dto.response.GetShowTimesDTO;
import com.bookmyshow.movie_booking_system.service.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mxmovies/v1")
public class ShowController {
    private static final Logger log = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    ShowService showService;

    @GetMapping("/showtimes/{showTimeId}/seats")
    public ResponseEntity<GetShowTimeSeatLayoutDTO> getSeatsForShowTime(@PathVariable long showTimeId) {
        log.info("*** FETCHING SEATS FOR SHOWTIME:{} ****", showTimeId);
        GetShowTimeSeatLayoutDTO showTimeSeatLayoutDTO = showService.getSeatsForShowTime(showTimeId);
        log.info("*** FETCHED SEATS FOR SHOWTIME:{} ****", showTimeId);
        return ResponseEntity.status(200).body(showTimeSeatLayoutDTO);
    }

    @GetMapping("/showtimes/{showTimeId}/bookedSeats")
    public ResponseEntity<GetShowTimeSeatLayoutDTO> getBookedSeatsForShowTime(@PathVariable long showTimeId) {
        log.info("*** FETCHING BOOKED SEATS FOR SHOWTIME:{} ****", showTimeId);
        GetShowTimeSeatLayoutDTO showTimeSeatLayoutDTO = showService.getBookedSeatsForShowTime(showTimeId);
        log.info("*** FETCHED BOOKED SEATS FOR SHOWTIME:{} ****", showTimeId);
        return ResponseEntity.status(200).body(showTimeSeatLayoutDTO);
    }

    @GetMapping("/showtimes")
    public ResponseEntity<GetShowTimesDTO> getShowTimeForDay(@RequestParam("cinemaId") long cinemaId, @RequestParam("movieId") long movieId, @RequestParam("showDate") String showDate) {
        log.info("**** FETCHING  SHOWTIME FOR SHOW DATE: {}, CINEMA: {}, MOVIE: {}", showDate, cinemaId, movieId);
        GetShowTimesDTO getShowTimesDTO = showService.getShowTimesForDay(cinemaId, movieId, showDate);
        log.info("**** FETCHED  SHOWTIME FOR SHOW DATE: {}, CINEMA: {}, MOVIE: {}", showDate, cinemaId, movieId);
        return ResponseEntity.status(200).body(getShowTimesDTO);
    }
}
