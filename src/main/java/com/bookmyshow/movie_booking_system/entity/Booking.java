package com.bookmyshow.movie_booking_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String paymentStatus;

    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id",nullable = false)
    private ShowTime showtime;

    @ManyToMany
    @JoinTable(name = "booking_seat", joinColumns = @JoinColumn(name = "booking_id"),inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seatsBooked = new ArrayList<>();

}
