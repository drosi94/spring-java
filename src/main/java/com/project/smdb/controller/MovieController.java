package com.project.smdb.controller;

import com.project.smdb.domain.Movie;
import com.project.smdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.create(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.update(id, movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

//    @GetMapping("/search")
//    public ResponseEntity<?> searchByName(@RequestParam String name) {
//        return ResponseEntity.ok(movieService.searchByName(name));
//    }
}
