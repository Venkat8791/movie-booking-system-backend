package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.GetShowTimeSeatLayoutDTO;
import com.bookmyshow.movie_booking_system.dto.GetShowTimesDTO;
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
    public ResponseEntity<GetShowTimeSeatLayoutDTO> getSeatsForShowTime(@PathVariable long showTimeId){
        GetShowTimeSeatLayoutDTO showTimeSeatLayoutDTO = showService.getSeatsForShowTime(showTimeId);
        return ResponseEntity.status(200).body(showTimeSeatLayoutDTO);
    }

    @GetMapping("/showtimes/{showTimeId}/bookedSeats")
    public ResponseEntity<GetShowTimeSeatLayoutDTO> getBookedSeatsForShowTime(@PathVariable long showTimeId){
        GetShowTimeSeatLayoutDTO showTimeSeatLayoutDTO = showService.getBookedSeatsForShowTime(showTimeId);
        return ResponseEntity.status(200).body(showTimeSeatLayoutDTO);
    }

    @GetMapping("/showtimes")
    public ResponseEntity<GetShowTimesDTO> getShowTimeForDay(@RequestParam("cinemaId") long cinemaId, @RequestParam("movieId") long movieId, @RequestParam("showDate") String showDate){
        log.info("Received request with showDate={}, cinemaId={}, movieId={}", showDate, cinemaId, movieId);
        GetShowTimesDTO getShowTimesDTO = showService.getShowTimesForDay(cinemaId,movieId,showDate);
        return  ResponseEntity.status(200).body(getShowTimesDTO);
    }
}
