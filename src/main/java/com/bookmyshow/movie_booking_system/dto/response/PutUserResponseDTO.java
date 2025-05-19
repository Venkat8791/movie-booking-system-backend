package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Data;

@Data
public class PutUserResponseDTO {
    private final String message;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    public PutUserResponseDTO(String message, String phoneNumber, String firstName, String lastName) {
        this.message = message;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
