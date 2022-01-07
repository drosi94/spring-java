package com.project.smdb.service;

import com.project.smdb.domain.Movie;

import java.util.List;

public interface MovieService {
    Movie create(Movie movie);
    Movie update(Long id, Movie movie);
    void delete(Long id);
    List<Movie> getAll();
    List<Movie> getByName(String name);
    Movie getById(Long id);
}
