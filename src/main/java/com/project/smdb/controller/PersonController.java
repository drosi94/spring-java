package com.project.smdb.controller;

import com.project.smdb.domain.Person;
import com.project.smdb.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class PersonController<T extends Person> {
    private final PersonService<T> personService;

    public PersonController(PersonService<T> personService) {
        this.personService = personService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody T person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody T person) {
        return ResponseEntity.ok(personService.update(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<?> all(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int limit) {
        return ResponseEntity.ok(personService.getAll(page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<T>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(personService.searchByName(name));
    }
}
