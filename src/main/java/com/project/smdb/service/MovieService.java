package com.project.smdb.service;

import com.project.smdb.domain.Movie;
import com.project.smdb.domain.projection.IMovieCountPerGenre;
import com.project.smdb.domain.projection.IMovieCountPerYearAndGenre;
import com.project.smdb.domain.type.MovieType;
import com.project.smdb.domain.type.PersonRole;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MovieService {
    Movie create(Movie movie);
    Movie update(Long id, Movie movie);
    void delete(Long id);
    Page<Movie> getAll(int page, int limit);
    List<Movie> getByType(MovieType type);
    Movie getById(Long id);
    List<Movie> getByGenreName(String genreName);
    List<Movie> getTopXHighRated(int x);
    List<IMovieCountPerGenre> countPerGenre();
    List<IMovieCountPerYearAndGenre> countPerYearAndGenre();
    Set<Movie> getByPersonName(String fullName);
    List<Movie> getByPersonNameAndRole(String fullName, PersonRole role);
    Map<String, List<Movie>> getByPersonNamePerGenre(String fullName);
}
