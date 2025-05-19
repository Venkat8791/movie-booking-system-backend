package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
    private final Long userId;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    public AuthResponseDTO(Long userId, String email, String firstName, String lastName, String phoneNumber) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }
}
