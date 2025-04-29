package com.bookmyshow.movie_booking_system.exception.dto;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
