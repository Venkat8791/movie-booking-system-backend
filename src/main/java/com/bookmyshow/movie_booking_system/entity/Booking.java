package com.bookmyshow.movie_booking_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentStatus;

    private double totalPrice;

    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id",nullable = false)
    private ShowTime showtime;

    @ManyToMany
    @JoinTable(name = "booking_show_seat", joinColumns = @JoinColumn(name = "booking_id"),inverseJoinColumns = @JoinColumn(name = "show_seat_id"))
    private List<ShowSeat> seatsBooked = new ArrayList<>();

    public void addSeat(ShowSeat showSeat){
        this.seatsBooked.add(showSeat);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
