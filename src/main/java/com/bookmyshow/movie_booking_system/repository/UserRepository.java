package com.bookmyshow.movie_booking_system.repository;

import com.bookmyshow.movie_booking_system.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
