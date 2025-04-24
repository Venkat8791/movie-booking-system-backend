package com.bookmyshow.movie_booking_system.exception.dto;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(String message){
        super(message);
    }
}
