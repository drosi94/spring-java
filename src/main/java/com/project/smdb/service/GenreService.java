package com.project.smdb.service;

import com.project.smdb.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre create(Genre genre);
    Genre update(Long id, Genre genre);
    void delete(Long id);
    List<Genre> getAll();
    List<Genre> searchByName(String name);
    Genre getById(Long id);
}
