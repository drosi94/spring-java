package com.project.smdb.repository;

import com.project.smdb.domain.Movie;
import com.project.smdb.domain.MovieType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findAllByType(MovieType movieType);

}