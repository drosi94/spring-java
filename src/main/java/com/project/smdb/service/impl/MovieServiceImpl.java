package com.project.smdb.service.impl;

import com.project.smdb.domain.*;
import com.project.smdb.domain.projection.IMovieCountPerGenre;
import com.project.smdb.domain.projection.IMovieCountPerYearAndGenre;
import com.project.smdb.domain.type.MovieType;
import com.project.smdb.domain.type.PersonRole;
import com.project.smdb.exception.ResourceNotFoundException;
import com.project.smdb.repository.ActorRepository;
import com.project.smdb.repository.DirectorRepository;
import com.project.smdb.repository.MovieRepository;
import com.project.smdb.repository.ProducerRepository;
import com.project.smdb.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        movie.setId(id);
        return movieRepository.save(buildMovie(movie));
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Page<Movie> getAll(int page, int limit) {
        return movieRepository.findAll(PageRequest.of(page, limit));
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

    @Override
    public List<Movie> getByGenreName(String genreName) {
        return movieRepository.findAllByGenreName(genreName);
    }

    @Override
    public List<Movie> getTopXHighRated(int x) {
        return movieRepository.findAll(PageRequest.of(0, x, Sort.by(Sort.Direction.DESC, "rating")))
                .getContent();
    }

    @Override
    public List<IMovieCountPerGenre> countPerGenre() {
        return movieRepository.countMoviesPerGenre();
    }

    @Override
    public List<IMovieCountPerYearAndGenre> countPerYearAndGenre() {
        return movieRepository.countMoviesPerYearAndGenre();
    }

    @Override
    public Set<Movie> getByPersonName(String fullName) {
        Set<Movie> allMovies = new HashSet<>();
        List<Movie> moviesActor = movieRepository.findAllByActorName(fullName);
        List<Movie> moviesProducer = movieRepository.findAllByProducerName(fullName);
        List<Movie> moviesDirector = movieRepository.findAllByDirectorName(fullName);

        Stream.of(moviesActor, moviesProducer, moviesDirector).forEach(allMovies::addAll);

        return allMovies;
    }

    @Override
    public List<Movie> getByPersonNameAndRole(String fullName, PersonRole role) {
        switch (role) {
            case ACTOR:
                return movieRepository.findAllByActorName(fullName);
            case PRODUCER:
                return movieRepository.findAllByProducerName(fullName);
            case DIRECTOR:
                return movieRepository.findAllByDirectorName(fullName);
            default:
                throw new ResourceNotFoundException(0L);
        }
    }

    @Override
    public Map<String, List<Movie>> getByPersonNamePerGenre(String fullName) {
        return this.getByPersonName(fullName).stream()
                .collect(Collectors.groupingBy(movie -> movie.getGenre().getName()));
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
