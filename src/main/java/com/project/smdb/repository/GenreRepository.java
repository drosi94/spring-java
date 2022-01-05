package com.project.smdb.repository;

import com.project.smdb.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAllByNameContainsIgnoreCase(String name);
}