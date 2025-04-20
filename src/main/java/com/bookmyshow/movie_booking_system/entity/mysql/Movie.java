package com.bookmyshow.movie_booking_system.entity.mysql;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter // Only getters, no setters
@NoArgsConstructor(access = AccessLevel.PROTECTED) // For Hibernate
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int duration;
    private String genre;
    private double rating;
    private String posterUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection(targetClass = LanguageType.class)
    @CollectionTable(name="movie_languages",joinColumns = @JoinColumn(name = "movie_id"))
    @Enumerated(EnumType.STRING)
    private List<LanguageType> languages;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ShowTime> showTimes = new ArrayList<>();

    @Override
    public String toString() {
        return "Movie{" +
                "duration=" + duration +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}
