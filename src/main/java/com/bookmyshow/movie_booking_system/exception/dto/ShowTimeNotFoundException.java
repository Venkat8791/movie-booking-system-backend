package com.bookmyshow.movie_booking_system.exception.dto;

public class ShowTimeNotFound extends RuntimeException{
    public ShowTimeNotFound(String message){
        super(message);
    }
}
