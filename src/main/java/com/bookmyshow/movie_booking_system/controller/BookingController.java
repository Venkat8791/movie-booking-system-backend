package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.BookingDetailsDTO;
import com.bookmyshow.movie_booking_system.dto.request.BookingRequestDTO;
import com.bookmyshow.movie_booking_system.dto.response.BookingResponseDTO;
import com.bookmyshow.movie_booking_system.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mxmovies/v1")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> handleBooking(@RequestBody @Valid BookingRequestDTO bookingDTO) {
        BookingResponseDTO bookingResponseDTO = bookingService.addBooking(bookingDTO);
        return ResponseEntity.status(200).body(bookingResponseDTO);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> getBooking(@PathVariable long bookingId) {
        BookingDetailsDTO bookingDetailsDTO = bookingService.getBooking(bookingId);
        return ResponseEntity.status(200).body(bookingDetailsDTO);
    }
}
