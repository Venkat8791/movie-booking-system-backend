package com.bookmyshow.movie_booking_system.repository;

import com.bookmyshow.movie_booking_system.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT U FROM User U WHERE U.email= :email")
    Optional<User> findByEmail(String email);

    @Query("SELECT U FROM User U WHERE U.phoneNumber= :phoneNumber")
    Optional<User> findByPhoneNumber(String phoneNumber);
}
