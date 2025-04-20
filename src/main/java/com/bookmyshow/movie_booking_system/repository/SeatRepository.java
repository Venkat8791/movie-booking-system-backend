package com.bookmyshow.movie_booking_system.repository;

import com.bookmyshow.movie_booking_system.entity.mysql.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Query("SELECT S FROM Seat S WHERE S.screen.id = :screenId")
    List<Seat> findByScreen(Long screenId);
}
