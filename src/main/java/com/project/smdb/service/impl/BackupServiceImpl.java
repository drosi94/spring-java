package com.project.smdb.service.impl;

import com.project.smdb.DataLoader;
import com.project.smdb.domain.*;
import com.project.smdb.exception.ServerErrorException;
import com.project.smdb.repository.*;
import com.project.smdb.service.BackupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackupServiceImpl implements BackupService {
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final String CSV_FOLDER = "src/main/resources/static/backup/";

    private final ActorRepository actorRepository;
    private final ProducerRepository producerRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public BackupServiceImpl(ActorRepository actorRepository, ProducerRepository producerRepository,
                             DirectorRepository directorRepository, GenreRepository genreRepository,
                             MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.producerRepository = producerRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Map<String, List<String>> generateBackup() {
        try {
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormatter.format(new Date());

            String actorsFilePath = "actors_" + currentDateTime + ".csv";
            String producersFilePath = "producers_" + currentDateTime + ".csv";
            String directorsFilePath = "directors_" + currentDateTime + ".csv";
            String genresFilePath = "genres_" + currentDateTime + ".csv";
            String moviesFilePath = "movies_" + currentDateTime + ".csv";

            long actorsSize = generateActorsCSV(CSV_FOLDER + actorsFilePath);
            long producersSize = generateProducersCSV(CSV_FOLDER + producersFilePath);
            long directorsSize = generateDirectorsCSV(CSV_FOLDER + directorsFilePath);
            long genresSize = generateGenresCSV(CSV_FOLDER + genresFilePath);
            long moviesSize = generateMoviesCSV(CSV_FOLDER + moviesFilePath);

            Map<String, List<String>> reportValue = new HashMap<>();

            final String baseUrl =
                    ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/backup/download/";

            reportValue.put("actors", List.of(Long.toString(actorsSize), baseUrl + actorsFilePath));
            reportValue.put("producers", List.of(Long.toString(producersSize), baseUrl + producersFilePath));
            reportValue.put("directors", List.of(Long.toString(directorsSize), baseUrl + directorsFilePath));
            reportValue.put("genres", List.of(Long.toString(genresSize), baseUrl + genresFilePath));
            reportValue.put("movies", List.of(Long.toString(moviesSize), baseUrl + moviesFilePath));

            return reportValue;
        } catch (IOException ex) {
            logger.error("File does not exist");
            logger.error(ex.getMessage());

            throw new ServerErrorException();
        }

    }

    @Override
    public File getFile(String fileName) throws FileNotFoundException {
        File file = new File(CSV_FOLDER + fileName);
        if (!file.isFile() || !file.exists()) {
            throw new FileNotFoundException();
        }

        return file;
    }

    private long generateActorsCSV(String fileName) throws IOException {
        CsvBeanWriter csvWriter = createCSVWriter(fileName);
        String[] csvHeader = {"Actor ID", "First Name", "Last Name"};
        String[] nameMapping = {"id", "firstName", "lastName"};

        csvWriter.writeHeader(csvHeader);

        List<Actor> actors = (List<Actor>) actorRepository.findAll();
        for (Actor actor : actors) {
            csvWriter.write(actor, nameMapping);
        }
        csvWriter.close();

        return actors.size();
    }

    private long generateProducersCSV(String fileName) throws IOException {
        CsvBeanWriter csvWriter = createCSVWriter(fileName);
        String[] csvHeader = {"Producer ID", "First Name", "Last Name"};
        String[] nameMapping = {"id", "firstName", "lastName"};

        csvWriter.writeHeader(csvHeader);

        List<Producer> producers = (List<Producer>) producerRepository.findAll();
        for (Producer producer : producers) {
            csvWriter.write(producer, nameMapping);
        }
        csvWriter.close();

        return producers.size();
    }

    private long generateDirectorsCSV(String fileName) throws IOException {
        CsvBeanWriter csvWriter = createCSVWriter(fileName);
        String[] csvHeader = {"Director ID", "First Name", "Last Name"};
        String[] nameMapping = {"id", "firstName", "lastName"};

        csvWriter.writeHeader(csvHeader);

        List<Director> directors = (List<Director>) directorRepository.findAll();
        for (Director director : directors) {
            csvWriter.write(director, nameMapping);
        }
        csvWriter.close();

        return directors.size();
    }

    private long generateGenresCSV(String fileName) throws IOException {
        CsvBeanWriter csvWriter = createCSVWriter(fileName);
        String[] csvHeader = {"Genre ID", "Name"};
        String[] nameMapping = {"id", "name"};

        csvWriter.writeHeader(csvHeader);

        List<Genre> genres = (List<Genre>) genreRepository.findAll();
        for (Genre genre : genres) {
            csvWriter.write(genre, nameMapping);
        }
        csvWriter.close();

        return genres.size();
    }

    private long generateMoviesCSV(String fileName) throws IOException {
        CsvBeanWriter csvWriter = createCSVWriter(fileName);
        String[] csvHeader = {"Movie ID", "Name", "Plot", "Rating", "Release Year",
                "Genre", "Actors", "Producers", "Directors"};
        String[] nameMapping = {"id", "name", "plot", "rating", "releaseYear",
                "genre", "actors", "producers", "directors"};

        csvWriter.writeHeader(csvHeader);

        List<Movie> movies = (List<Movie>) movieRepository.findAll();

        for (Movie movie : movies) {
            csvWriter.write(movie, nameMapping);
        }
        csvWriter.close();

        return movies.size();
    }

    private CsvBeanWriter createCSVWriter(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);

        return new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);
    }
}
