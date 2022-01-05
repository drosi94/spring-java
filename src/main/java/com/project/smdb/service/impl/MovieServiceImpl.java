package com.project.smdb.service.impl;

import com.project.smdb.domain.Movie;
import com.project.smdb.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public Movie create(Movie movie) {
        return null;
    }

    @Override
    public Movie update(Long id, Movie movie) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Movie> getAll() {
        return null;
    }

    @Override
    public List<Movie> getByName(String name) {
        return null;
    }

    @Override
    public Movie getById() {
        return null;
    }
}
