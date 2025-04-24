package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//NOT BEING USED CURRENTLY

@RestController
@RequestMapping("/mxmovies/v1")
public class SeatController {

    @Autowired
    SeatService seatService;

    @PostMapping("/seats/{screenId}")
    public ResponseEntity<String> addSeatsForScreen(@PathVariable long screenId, @RequestHeader(name = "rows") int rows, @RequestHeader(name = "columns") int cols, @RequestHeader(name = "basePrice") int basePrice) {
        int status = seatService.insertSeatsForScreen(screenId, rows, cols, basePrice);
        if (status == 1) {
            return ResponseEntity.status(200).body("Seats inserted Successfully. Total inserted seats - " + (rows * cols));
        }
        return ResponseEntity.status(500).body("Failed to insert seats for screen - " + screenId);
    }
}
