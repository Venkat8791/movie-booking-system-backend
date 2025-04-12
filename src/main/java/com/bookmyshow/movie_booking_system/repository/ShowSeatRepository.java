package com.bookmyshow.movie_booking_system.repository;

import com.bookmyshow.movie_booking_system.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    @Query("SELECT s from ShowSeat s WHERe s.seat.id= :seatId AND s.showTime.id= :showTimeId")
    public ShowSeat findBySeatAndShowTime(long seatId,long showTimeId);
}
