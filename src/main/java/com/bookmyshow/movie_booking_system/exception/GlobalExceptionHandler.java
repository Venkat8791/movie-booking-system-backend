package com.bookmyshow.movie_booking_system.exception;


import com.bookmyshow.movie_booking_system.exception.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoMoviesException.class)
    public ResponseEntity<ErrorResponse> handleNoMoviesToShow(NoMoviesException ex){
        ErrorResponse error = new ErrorResponse("NO_MOVIES",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMoviesNotFound(MovieNotFoundException ex){
        ErrorResponse error = new ErrorResponse("MOVIE_NOT_FOUND",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ShowTimesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleShowTimesNotFound(ShowTimesNotFoundException ex){
        ErrorResponse error = new ErrorResponse("NO_SHOWS",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(LayoutDocumentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLayoutDocumentNotFound(LayoutDocumentNotFoundException ex){
        ErrorResponse error = new ErrorResponse("LAYOUT_NOT_FOUND",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(SeatsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSeatsNotFound(SeatsNotFoundException ex){
        ErrorResponse error = new ErrorResponse("NO_SEATS_ADDED",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ShowTimeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleShowTimeNotFound(ShowTimesNotFoundException ex){
        ErrorResponse error = new ErrorResponse("SHOW_TIME_NOT_FOUND",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOtp(InvalidOtpException ex){
        ErrorResponse error = new ErrorResponse("INVALID_OTP",ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        ErrorResponse error = new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }



}
