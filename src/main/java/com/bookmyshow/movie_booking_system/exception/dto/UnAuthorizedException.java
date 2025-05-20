package com.bookmyshow.movie_booking_system.exception.dto;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String message) {
        super(message);
    }
}
