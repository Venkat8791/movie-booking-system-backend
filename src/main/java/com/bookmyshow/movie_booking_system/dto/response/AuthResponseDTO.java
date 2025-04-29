package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
    private final Long userId;
    private final String email;
    private final String firstName;

    public AuthResponseDTO(Long userId, String email, String firstName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;

    }
}
