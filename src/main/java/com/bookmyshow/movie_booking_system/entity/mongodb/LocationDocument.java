package com.bookmyshow.movie_booking_system.entity.mongodb;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Locations")
@Data
public class LocationDocument {
    @Id
    private String id;

    private String city;
    private String state;
    private String country;

}
