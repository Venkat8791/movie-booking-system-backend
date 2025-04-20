package com.bookmyshow.movie_booking_system.entity.mysql;

import com.bookmyshow.movie_booking_system.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {

    @Id
    private String id;

    private String rowNum;
    private int columnNum;

    @Enumerated(EnumType.STRING)
    private SeatType seatType = SeatType.REGULAR;;


    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;


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
                ", screen=" + screen +
                '}';
    }
}

