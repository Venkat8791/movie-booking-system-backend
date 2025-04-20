package com.bookmyshow.movie_booking_system.entity.mongodb;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cinemas")
@Data
public class CinemaDocument {
    @Id
    private String id;
    private String cinemaName;

    @DBRef
    private LocationDocument locationDocument;
}
