package com.bookmyshow.movie_booking_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String phoneNumber;

    private String emailId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
