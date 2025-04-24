package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.service.ShowSeatDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mxmovies/v1")
public class DummyDataController {

    @Autowired
    ShowSeatDataService showSeatDataService;

    @PostMapping("populate-show-seats/{showTimeId}")
    public ResponseEntity<String> populateShowSeats(@PathVariable long showTimeId) {
        int res = showSeatDataService.createShowSeatsForShowTime(showTimeId);
        if (res == 1) {
            return ResponseEntity.status(200).body("Show Seats created for showTimeId: " + showTimeId);
        }
        return ResponseEntity.status(500).body("Failed to insert dummy data");
    }
}
