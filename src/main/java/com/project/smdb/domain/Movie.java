package com.project.smdb.domain;


import com.project.smdb.domain.type.MovieType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = -3302905745770369700L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 255, message = "Name can not be more than 255 characters")
    @NotBlank(message = "Name can not be empty")
    private String name;

    @Column(name = "plot")
    @Lob
    private String plot;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type can not be empty")
    private MovieType type;

    @Column(name = "duration", nullable = false)
    @NotNull(message = "Duration can not be empty")
    @Min(message = "Duration should be bigger than 0", value = 0)
    private Integer duration;

    @Column(name = "release_year", nullable = false)
    @NotNull(message = "Release year can not be empty")
    @Min(message = "Release year should be bigger than 1900", value = 1900)
    private Integer releaseYear;

    @Column(name = "rating", precision = 2, nullable = false)
    @NotNull(message = "Rating can not be empty")
    @Min(message = "Rating should be bigger than 0", value = 0)
    @Max(message = "Rating should be less than 10", value = 10)
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "Genre can not be empty")
    private Genre genre;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "movies_actors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    @NotNull(message = "Actors can not be empty")
    @Size(min = 1, message = "At least one actor should be present")
    private Set<Actor> actors;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "movies_directors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "director_id")}
    )
    @NotNull(message = "Directors can not be empty")
    @Size(min = 1, message = "At least one director should be present")
    private Set<Director> directors;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "movies_producers",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "producer_id")}
    )
    @NotNull(message = "Producers can not be empty")
    @Size(min = 1, message = "At least one producer should be present")
    private Set<Producer> producers;

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
    }

    public Set<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }
}