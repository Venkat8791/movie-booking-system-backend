package com.bookmyshow.movie_booking_system.dto;

import lombok.Getter;

@Getter
public class UserDTO {

    private final String username;

    private final String password;

    private final String phoneNumber;

    private final String emailId;

    public UserDTO( String username, String password,String emailId, String phoneNumber) {
        this.emailId = emailId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
