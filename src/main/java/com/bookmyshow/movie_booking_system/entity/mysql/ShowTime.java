package com.bookmyshow.movie_booking_system.entity.mysql;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

@Entity
@Data
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int availableSeats;

    private LocalDate showDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private LanguageType language = LanguageType.ENGLISH;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id",nullable = false)
    private Screen screen;

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking){
        this.getBookings().add(booking);
        booking.setShowTime(this);
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "id=" + id +
                ", showDate=" + showDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
