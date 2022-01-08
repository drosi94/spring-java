package com.project.smdb.domain.projection;

public interface IMovieCountPerYearAndGenre {
    String getGenreName();
    Integer getReleaseYear();
    Integer getMoviesCount();
}
