package com.project.smdb.service.impl;

import com.project.smdb.domain.Actor;
import com.project.smdb.repository.*;
import com.project.smdb.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BackupServiceImpl implements BackupService {

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
    public void generateBackup() {
        try {
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormatter.format(new Date());
            FileWriter fileWriter = new FileWriter(CSV_FOLDER+"actors_"+currentDateTime+".csv");

            ICsvBeanWriter csvWriter = new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"Actor ID", "First Name", "Last Name"};
            String[] nameMapping = {"id", "firstName", "lastName"};

            csvWriter.writeHeader(csvHeader);

            for (Actor actor : actorRepository.findAll()) {
                csvWriter.write(actor, nameMapping);
            }

            csvWriter.close();
        } catch(IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
