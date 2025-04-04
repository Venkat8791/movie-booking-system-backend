package com.bookmyshow.movie_booking_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    private List<Screen> screens = new ArrayList<>();

}
