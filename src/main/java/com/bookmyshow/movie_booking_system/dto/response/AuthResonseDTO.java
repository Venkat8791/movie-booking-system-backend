package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Getter;

@Getter
public class AuthResonseDTO {
    private final Long userId;
    private final String username;
    private final Long expiry;
    private final String token;

    public AuthResonseDTO(Long userId,String username, String token,Long expiry) {
        this.userId = userId;
        this.username = username;
        this.token = token;
        this.expiry = expiry;
    }
}
