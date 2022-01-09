package com.project.smdb.service.impl;

import com.project.smdb.domain.Genre;
import com.project.smdb.exception.ResourceNotFoundException;
import com.project.smdb.repository.GenreRepository;
import com.project.smdb.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Long id, Genre genre) {
        genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        genre.setId(id);
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Long id) {
        genreRepository.delete(genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public List<Genre> getAll() {
        return new ArrayList<>((Collection<Genre>) genreRepository.findAll());
    }

    @Override
    public List<Genre> searchByName(String name) {
        return genreRepository.findAllByNameContainsIgnoreCase(name);
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
