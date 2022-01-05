package com.project.smdb.controller;

import com.project.smdb.domain.Genre;
import com.project.smdb.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.create(genre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.update(id, genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(genreService.searchByName(name));
    }
}
