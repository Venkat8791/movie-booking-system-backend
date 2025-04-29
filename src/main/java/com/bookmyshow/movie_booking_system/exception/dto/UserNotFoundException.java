package com.bookmyshow.movie_booking_system.exception.dto;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
