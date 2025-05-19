package com.bookmyshow.movie_booking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PutUserRequestDTO {
    @NotNull
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    @NotNull
    private final String email;

    public PutUserRequestDTO(Long userId, String firstName, String lastName, String email, String phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
