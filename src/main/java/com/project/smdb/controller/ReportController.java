package com.project.smdb.controller;


import com.project.smdb.domain.type.PersonRole;
import com.project.smdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

    private final MovieService movieService;

    @Autowired
    public ReportController(MovieService movieService) {
        this.movieService = movieService;
    }

    // 2.2.6 1
    @GetMapping("/moviesTopRated")
    public ResponseEntity<?> topXHighRatedMovies(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(movieService.getTopXHighRated(limit));
    }


    // 2.2.6 2
    @GetMapping("/moviesByPerson")
    public ResponseEntity<?> moviesByPersonName(@RequestParam String fullName) {
        return ResponseEntity.ok(movieService.getByPersonName(fullName));
    }

    // 2.2.6 3
    @GetMapping("/moviesByPersonAndRole")
    public ResponseEntity<?> moviesByPersonNameAndRole(@RequestParam String fullName, @RequestParam PersonRole role) {
        return ResponseEntity.ok(movieService.getByPersonNameAndRole(fullName, role));
    }

    // 2.2.6 4
    @GetMapping("/moviesByGenre")
    public ResponseEntity<?> moviesByGenreName(@RequestParam String name) {
        return ResponseEntity.ok(movieService.getByGenreName(name));
    }

    // 2.2.6 5
    @GetMapping("/moviesPerGenre")
    public ResponseEntity<?> numberOfMoviesPerGenre() {
        return ResponseEntity.ok(movieService.countPerGenre());
    }

    // 2.2.6 6
    @GetMapping("/moviesPerYearAndGenre")
    public ResponseEntity<?> numberOfMoviesPerYearAndGenre() {
        return ResponseEntity.ok(movieService.countPerYearAndGenre());
    }

    // 2.2.6 7
    @GetMapping("/moviesByPersonPerGenre")
    public ResponseEntity<?> moviesByPersonPerGenre(@RequestParam String fullName) {
        return ResponseEntity.ok(movieService.getByPersonNamePerGenre(fullName));
    }
}
