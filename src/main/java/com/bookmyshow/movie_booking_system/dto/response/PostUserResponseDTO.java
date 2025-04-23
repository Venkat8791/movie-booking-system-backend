package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

@Getter
public class PostUserResponseDTO {
    private final Long id;
    private final String username;
    private final String emailId;
    private final String phoneNumber;


    public PostUserResponseDTO(Long id, String username, String emailId, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }
}
