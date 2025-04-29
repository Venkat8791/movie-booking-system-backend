package com.bookmyshow.movie_booking_system.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginDTO {
    @NotNull
    private final String email;
    @NotNull
    private final String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
