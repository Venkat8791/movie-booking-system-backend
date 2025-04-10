package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.GetShowTimeSeatLayoutDTO;
import com.bookmyshow.movie_booking_system.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mxmovies/v1")
public class ShowController {

    @Autowired
    ShowService showService;

    @GetMapping("/showtimes/{showTimeId}/seats")
    public ResponseEntity<GetShowTimeSeatLayoutDTO> getSeatsForShowTime(@PathVariable long showTimeId){
        GetShowTimeSeatLayoutDTO showTimeSeatLayoutDTO = showService.getSeatsForShowTime(showTimeId);
        return ResponseEntity.status(200).body(showTimeSeatLayoutDTO);

    }
}
