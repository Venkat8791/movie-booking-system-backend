package com.bookmyshow.movie_booking_system.exception.dto;

public class InvalidOtpException extends RuntimeException{
    public InvalidOtpException(String message){
        super(message);
    }
}
