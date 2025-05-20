package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.BookingDetailsDTO;
import com.bookmyshow.movie_booking_system.dto.request.BookingRequestDTO;
import com.bookmyshow.movie_booking_system.dto.response.BookingResponseDTO;
import com.bookmyshow.movie_booking_system.exception.dto.UnAuthorizedException;
import com.bookmyshow.movie_booking_system.service.BookingService;
import com.bookmyshow.movie_booking_system.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mxmovies/v1")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> handleBooking(HttpServletRequest request, @RequestBody @Valid BookingRequestDTO bookingDTO) {
        String token = jwtService.extractJwtFromCookie(request);
        if (token == null || !jwtService.validateToken(token)) {
            throw new UnAuthorizedException("You are unauthorized. Please login to continue");
        }
        String email = jwtService.extractEmail(token);
        BookingResponseDTO bookingResponseDTO = bookingService.addBooking(bookingDTO, email);
        return ResponseEntity.status(200).body(bookingResponseDTO);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> getBooking(@PathVariable long bookingId) {
        BookingDetailsDTO bookingDetailsDTO = bookingService.getBooking(bookingId);
        return ResponseEntity.status(200).body(bookingDetailsDTO);
    }
}
