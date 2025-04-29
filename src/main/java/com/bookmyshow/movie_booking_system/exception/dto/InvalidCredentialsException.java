package com.bookmyshow.movie_booking_system.exception.dto;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
