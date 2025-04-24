package com.bookmyshow.movie_booking_system.exception.dto;

public class NoMoviesException extends RuntimeException{

    public NoMoviesException(String message){
        super(message);
    }
}
