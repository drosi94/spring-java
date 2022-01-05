package com.project.smdb;

import com.github.javafaker.Faker;
import com.project.smdb.domain.*;
import com.project.smdb.repository.*;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.jeasy.random.FieldPredicates.named;


@Component
public class DataLoader implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private static Faker faker = new Faker();


    private ActorRepository actorRepository;
    private ProducerRepository producerRepository;
    private DirectorRepository directorRepository;
    private GenreRepository genreRepository;
    private MovieRepository movieRepository;

    @Autowired
    public DataLoader(ActorRepository actorRepository, ProducerRepository producerRepository, DirectorRepository directorRepository, GenreRepository genreRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.producerRepository = producerRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    public void run(ApplicationArguments args) {
        logger.debug("Data loading started");

        var actors = generateRandomActors(200);
        var producers = generateRandomProducers(30);
        var directors = generateRandomDirectors(20);

        var genres = generateGenres();

        actorRepository.saveAll(actors);
        producerRepository.saveAll(producers);
        directorRepository.saveAll(directors);
        genreRepository.saveAll(genres);

        var movies = generateRandomMovies(200);

        movieRepository.saveAll(movies);
    }

    private Set<Actor> generateRandomActors(int size) {
        logger.debug("Generating actors...");

        Set<Actor> actors = new HashSet<>();
        IntStream.range(0, size).forEach(i -> actors.add(randomPersonConfiguration().nextObject(Actor.class)));

        return actors;
    }

    private Set<Producer> generateRandomProducers(int size) {
        logger.debug("Generating producers...");

        Set<Producer> producers = new HashSet<>();
        IntStream.range(0, size).forEach(i -> producers.add(randomPersonConfiguration().nextObject(Producer.class)));

        return producers;
    }

    private Set<Director> generateRandomDirectors(int size) {
        logger.debug("Generating directors...");

        Set<Director> directors = new HashSet<>();
        IntStream.range(0, size).forEach(i -> directors.add(randomPersonConfiguration().nextObject(Director.class)));

        return directors;
    }

    private Set<Genre> generateGenres() {
        logger.debug("Generating genres...");

        Set<Genre> genres = new HashSet<>();
        var possibleGenres = new String[]{"Action", "Drama", "Romance", "Horror", "Comedy", "Thriller"};

        Arrays.stream(possibleGenres).forEach((name) -> {
            Genre genre = new Genre();
            genre.setName(name);

            genres.add(genre);
        });

        return genres;
    }

    private Set<Movie> generateRandomMovies(int size) {
        logger.debug("Generating movies...");

        EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(named("name"), () -> faker.book().title())
                .randomize(named("plot"), () -> faker.lorem().paragraph(4))
                .randomize(named("duration"), () -> faker.random().nextInt(60, 400))
                .randomize(named("releaseYear"), () -> faker.random().nextInt(1950, 2022))
                .randomize(named("rating"), () -> faker.number().randomDouble(2, 0, 10))
                .excludeField(named("id"));

        var movieRandomizer =  new EasyRandom(parameters);

        Set<Movie> movies = new HashSet<>();
        IntStream.range(0, size).forEach(i -> movies.add(movieRandomizer.nextObject(Movie.class)));

        return movies;
    }

    private EasyRandom randomPersonConfiguration() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(named("firstName"), () -> faker.name().firstName()).randomize(named("lastName"),
                        () -> faker.name().lastName())
                .excludeField(named("id"));
        return new EasyRandom(parameters);
    }

}