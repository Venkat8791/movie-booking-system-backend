package com.bookmyshow.movie_booking_system.entity;

import com.bookmyshow.movie_booking_system.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rowNum;
    private int columnNum;

    @Enumerated(EnumType.STRING)
    private SeatType seatType = SeatType.REGULAR;;

    private int price;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @ManyToMany(mappedBy = "seatsBooked")
    private List<Booking> bookings;


    public String getSeatNumber(){
        return this.rowNum + this.columnNum;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", rowNum='" + rowNum + '\'' +
                ", columnNum=" + columnNum +
                ", seatType=" + seatType +
                ", price=" + price +
                ", screen=" + screen +
                '}';
    }
}

