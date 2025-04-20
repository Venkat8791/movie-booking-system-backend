package com.bookmyshow.movie_booking_system.entity.mongodb;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Layout")
@Data
public class LayoutDocument {

    @Id
    private String id;

    private long screenId;

    private long cinemaId;

    private List<SeatSection> sections;
}

