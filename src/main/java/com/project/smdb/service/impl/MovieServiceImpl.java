package com.project.smdb.service.impl;

import com.project.smdb.domain.*;
import com.project.smdb.exception.ResourceNotFoundException;
import com.project.smdb.repository.ActorRepository;
import com.project.smdb.repository.DirectorRepository;
import com.project.smdb.repository.MovieRepository;
import com.project.smdb.repository.ProducerRepository;
import com.project.smdb.service.MovieService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
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

        return movieRepository.save(buildMovie(movie));
    }

    @Override
    public Movie update(Long id, Movie movie) {
        movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return movieRepository.save(buildMovie(movie));
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public List<Movie> getAll() {
        return new ArrayList<>((Collection<Movie>) movieRepository.findAll());
    }

    @Override
    public List<Movie> getByType(MovieType type) {
        return movieRepository.findAllByType(type);
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private Movie buildMovie(Movie movie) {
        Set<Actor> actors = movie.getActors().stream()
                .map(person -> actorRepository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(person.getId())))
                .collect(Collectors.toCollection(HashSet::new));

        Set<Director> directors = movie.getDirectors().stream()
                .map(person -> directorRepository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(person.getId())))
                .collect(Collectors.toCollection(HashSet::new));

        Set<Producer> producers = movie.getProducers().stream()
                .map(person -> producerRepository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(person.getId())))
                .collect(Collectors.toCollection(HashSet::new));

        movie.setActors(actors);
        movie.setDirectors(directors);
        movie.setProducers(producers);

        return movie;
    }
}
