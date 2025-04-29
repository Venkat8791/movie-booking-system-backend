package com.bookmyshow.movie_booking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserDTO {

    private final String username;

    @NotNull
    private final String phoneNumber;

    private final String emailId;

    public UserDTO(String username, String emailId, String phoneNumber) {
        this.emailId = emailId;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
}
