package com.bookmyshow.movie_booking_system.dto.response;

import lombok.Getter;

@Getter
public class PostUserResponseDTO {
    private final Long id;
    private final String email;
    private final String signupMessage;


    public PostUserResponseDTO(Long id, String email, String signupMessage) {
        this.id = id;
        this.email = email;
        this.signupMessage = signupMessage;
    }
}
