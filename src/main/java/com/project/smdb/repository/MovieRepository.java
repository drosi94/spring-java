package com.project.smdb.repository;

import com.project.smdb.domain.Movie;
import com.project.smdb.domain.projection.IMovieCountPerGenre;
import com.project.smdb.domain.projection.IMovieCountPerYearAndGenre;
import com.project.smdb.domain.type.MovieType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    List<Movie> findAllByType(MovieType movieType);

    List<Movie> findAllByGenreName(String genreName);

    @Query("SELECT DISTINCT m FROM Movie AS m JOIN m.actors AS a WHERE a.fullName = :fullName")
    List<Movie> findAllByActorName(@Param("fullName") String fullName);

    @Query("SELECT DISTINCT m FROM Movie AS m JOIN m.producers AS p WHERE p.fullName = :fullName")
    List<Movie> findAllByProducerName(@Param("fullName") String fullName);

    @Query("SELECT DISTINCT m FROM Movie AS m JOIN m.directors AS d WHERE d.fullName = :fullName")
    List<Movie> findAllByDirectorName(@Param("fullName") String fullName);

    @Query("SELECT COUNT(m) as moviesCount, m.genre.name as genreName FROM Movie AS m GROUP BY m.genre")
    List<IMovieCountPerGenre> countMoviesPerGenre();

    @Query("SELECT COUNT(m) as moviesCount, m.releaseYear as releaseYear, m.genre.name as genreName FROM Movie AS m GROUP BY m.releaseYear, m.genre")
    List<IMovieCountPerYearAndGenre> countMoviesPerYearAndGenre();
}