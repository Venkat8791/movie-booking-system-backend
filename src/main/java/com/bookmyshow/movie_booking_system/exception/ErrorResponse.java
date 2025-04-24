package com.bookmyshow.movie_booking_system.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorCode;
    private String message;

    public ErrorResponse(String errorCode,String message){
        this.errorCode = errorCode;
        this.message = message;
    }

}
