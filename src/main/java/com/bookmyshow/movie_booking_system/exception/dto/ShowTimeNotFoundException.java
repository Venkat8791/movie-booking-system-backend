package com.bookmyshow.movie_booking_system.exception.dto;

public class ShowTimeNotFoundException extends RuntimeException{
    public ShowTimeNotFoundException(String message){
        super(message);
    }
}
