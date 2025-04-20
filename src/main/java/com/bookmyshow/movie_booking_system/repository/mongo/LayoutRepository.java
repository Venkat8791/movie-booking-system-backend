package com.bookmyshow.movie_booking_system.repository.mongo;

import com.bookmyshow.movie_booking_system.entity.mongodb.LayoutDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LayoutRepository extends MongoRepository<LayoutDocument,Long> {
    Optional<LayoutDocument> findByScreenId(long screenId);
}
