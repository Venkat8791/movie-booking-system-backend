package com.bookmyshow.movie_booking_system.entity.mongodb;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Screens")
@Data
public class ScreenDocument {

    @Id
    private String id;
    private String screenName;

    @DBRef
    private CinemaDocument cinemaDocument;

}
