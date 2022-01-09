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

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static org.jeasy.random.FieldPredicates.named;


@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private static final Faker faker = new Faker(new Random(24));

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public DataLoader(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    public void run(ApplicationArguments args) {
        logger.debug("Data loading started");

        var genres = generateGenres();
        genreRepository.saveAll(genres);

        var movies = generateRandomMovies(500, genres);

        movieRepository.saveAll(movies);
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

    private Set<Movie> generateRandomMovies(int size, Set<Genre> genres) {
        logger.debug("Generating movies...");

        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L)
                .objectPoolSize(200)
                .randomize(named("name"), () -> faker.book().title())
                .randomize(named("plot"), () -> faker.lorem().paragraph(4))
                .randomize(named("duration"), () -> faker.random().nextInt(60, 400))
                .randomize(named("releaseYear"), () -> faker.random().nextInt(1950, 2022))
                .randomize(named("rating"), () -> faker.number().randomDouble(2, 0, 10))
                .randomize(named("genre"), () -> genres.toArray()[faker.random().nextInt(0, genres.size() - 1)])
                .randomize(named("firstName"), () -> faker.name().firstName())
                .randomize(named("lastName"), () -> faker.name().lastName())
                .collectionSizeRange(3, 6)
                .excludeField(named("id"))
                .excludeField(named("movies"));

        var movieRandomizer = new EasyRandom(parameters);

        Set<Movie> movies = new HashSet<>();
        IntStream.range(0, size).forEach(i -> movies.add(movieRandomizer.nextObject(Movie.class)));

        return movies;
    }
}