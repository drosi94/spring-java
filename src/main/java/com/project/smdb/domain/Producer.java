package com.project.smdb.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "producers")
public class Producer extends Person {
    @ManyToMany(mappedBy = "producers")
    @JsonBackReference
    private Set<Movie> movies;

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
        movie.getProducers().remove(this);
    }
}