package com.bookmyshow.movie_booking_system.repository;

import com.bookmyshow.movie_booking_system.entity.ShowTime;
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


}

