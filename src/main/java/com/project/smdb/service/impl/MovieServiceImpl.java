package com.project.smdb.service.impl;

import com.project.smdb.domain.Actor;
import com.project.smdb.domain.Director;
import com.project.smdb.domain.Movie;
import com.project.smdb.domain.Producer;
import com.project.smdb.exception.ResourceNotFoundException;
import com.project.smdb.repository.ActorRepository;
import com.project.smdb.repository.DirectorRepository;
import com.project.smdb.repository.MovieRepository;
import com.project.smdb.repository.ProducerRepository;
import com.project.smdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final ProducerRepository producerRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ActorRepository actorRepository, DirectorRepository directorRepository, ProducerRepository producerRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public Movie create(Movie movie) {
        Set<Actor> actors = movie.getActors().stream().map(person -> {
            return actorRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException(person.getId()));
        }).collect(Collectors.toCollection(HashSet::new));

        Set<Director> directors = movie.getDirectors().stream().map(person -> {
            return directorRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException(person.getId()));
        }).collect(Collectors.toCollection(HashSet::new));

        Set<Producer> producers = movie.getProducers().stream().map(person -> {
            return producerRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException(person.getId()));
        }).collect(Collectors.toCollection(HashSet::new));

        movie.setActors(actors);
        movie.setDirectors(directors);
        movie.setProducers(producers);
        return this.movieRepository.save(movie);
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
    public Movie getById(Long id) {
        return null;
    }
}
