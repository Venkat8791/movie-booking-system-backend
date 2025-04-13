package com.bookmyshow.movie_booking_system.repository;

import com.bookmyshow.movie_booking_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("""
    SELECT b FROM Booking b
    JOIN FETCH b.showTime st
    JOIN FETCH st.screen s
    JOIN FETCH s.cinema c
    JOIN FETCH st.movie m
    JOIN FETCH b.seatsBooked ss
    JOIN FETCH ss.seat seat
    WHERE b.id = :bookingId
""")
    Booking fetchBookingWithDetails(@Param("bookingId") Long bookingId);
}
