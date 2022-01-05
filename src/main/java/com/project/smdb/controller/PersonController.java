package com.project.smdb.controller;

import com.project.smdb.domain.Person;
import com.project.smdb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class PersonController<T extends Person> {
    private final PersonService<T> personService;

    @Autowired
    public PersonController(PersonService<T> personService) {
        this.personService = personService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody T person) {
        return ResponseEntity.ok(personService.create(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody T person) {
        return ResponseEntity.ok(personService.update(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Person>> searchByName(@RequestParam String name) {
//        return ResponseEntity.ok(personService.searchByName(name));
//    }
}
