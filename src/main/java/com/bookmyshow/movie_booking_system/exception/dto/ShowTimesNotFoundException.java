package com.bookmyshow.movie_booking_system.exception.dto;

public class ShowTimesNotFoundException extends RuntimeException{

    public ShowTimesNotFoundException(String message){
        super(message);
    }
}
