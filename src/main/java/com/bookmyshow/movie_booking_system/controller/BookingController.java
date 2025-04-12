package com.bookmyshow.movie_booking_system.controller;


import com.bookmyshow.movie_booking_system.dto.BookingDTO;
import com.bookmyshow.movie_booking_system.dto.PostBookingResponseDTO;
import com.bookmyshow.movie_booking_system.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mxmovies/v1")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<PostBookingResponseDTO> handleBooking(@RequestBody BookingDTO bookingDTO){
        PostBookingResponseDTO postBookingResponseDTO = bookingService.addBooking(bookingDTO);
        return ResponseEntity.status(200).body(postBookingResponseDTO);
    }
}
