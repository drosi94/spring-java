package com.project.smdb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="plot", nullable = false)
    @Lob
    private String plot;

    @Column(name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieType type;

    @Column(name="duration", nullable = false)
    private Integer duration;

    @Column(name="release_year", nullable = false)
    private Integer releaseYear;

    @Column(name="rating", precision = 2, nullable = false)
    private Double rating;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movies_actors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private Set<Actor> actors;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movies_directors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "director_id")}
    )
    private Set<Director> directors ;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movies_producers",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "producer_id")}
    )
    private Set<Producer> producers ;

    public Movie() {
    }
}