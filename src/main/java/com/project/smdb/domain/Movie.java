package com.project.smdb.domain;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="plot")
    @Lob
    private String plot;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private MovieType type;

    @Column(name="duration")
    private Integer duration;

    @Column(name="release_year")
    private Integer releaseYear;

    @Column(name="rating", precision = 2)
    private Double rating;

    public Movie() {
    }
}