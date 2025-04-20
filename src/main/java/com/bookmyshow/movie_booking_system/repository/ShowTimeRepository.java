package com.bookmyshow.movie_booking_system.repository;

import com.bookmyshow.movie_booking_system.entity.mysql.ShowSeat;
import com.bookmyshow.movie_booking_system.entity.mysql.ShowTime;
import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime,Long> {

    @Query("SELECT s FROM ShowTime s WHERE s.movie.id = :movieId AND s.showDate= :date")
    List<ShowTime> fetchShowTimesForMovie(@Param("movieId") long movieId, @Param("date") LocalDate date);

    @Query("SELECT ss FROM ShowSeat ss JOIN FETCH ss.seat WHERE ss.showTime.id = :showTimeId")
    List<ShowSeat> findSeatsByShowTime(@Param("showTimeId") Long showTimeId);

    @Query("SELECT ss FROM ShowSeat ss JOIN FETCH ss.seat WHERE ss.showTime.id = :showTimeId AND ss.status = :seatStatus")
    List<ShowSeat> findBookedSeatsByShowTime(@Param("showTimeId") Long showTimeId,@Param("seatStatus") SeatStatus seatStatus);

    @Query("SELECT st from ShowTime st JOIN FETCH st.movie m JOIN FETCH st.screen s where st.movie.id= :movieId AND st.showDate= :showDate AND s.cinema.id= :cinemaId")
    List<ShowTime> findShowsByMovieIdAndShowDate(long cinemaId,long movieId, LocalDate showDate);
}

