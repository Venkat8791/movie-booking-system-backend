package com.bookmyshow.movie_booking_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String screenName;

    private int totalSeats;

    @ManyToOne
    @JoinColumn(name = "cinema_id",nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    List<ShowTime> showTimes = new ArrayList<>();

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    List<Seat> seats = new ArrayList<>();

}
