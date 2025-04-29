package com.bookmyshow.movie_booking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PutUserDTO {
    @NotNull
    private final Long userId;
    private final String username;

    @NotNull
    private final String phoneNumber;

    private final String emailId;

    public PutUserDTO(String emailId, Long userId, String username, String phoneNumber) {
        this.emailId = emailId;
        this.userId = userId;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PutUserDTO{" +
                "emailId='" + emailId + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
