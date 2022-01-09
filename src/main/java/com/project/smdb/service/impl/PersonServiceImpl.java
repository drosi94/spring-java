package com.project.smdb.service.impl;

import com.project.smdb.domain.Movie;
import com.project.smdb.domain.Person;
import com.project.smdb.exception.ResourceNotFoundException;
import com.project.smdb.repository.PersonRepository;
import com.project.smdb.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public abstract class PersonServiceImpl<T extends Person> implements PersonService<T> {

    protected final PersonRepository<T> personRepository;

    public PersonServiceImpl(PersonRepository<T> personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public T create(T person) {
        return personRepository.save(person);
    }

    @Override
    public T update(Long id, T person) {
        personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        person.setId(id);
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        T person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if (person.getMovies().size() > 0) {
            for (Movie movie: person.getMovies()){
                person.removeMovie(movie);
            }
        }

        personRepository.delete(person);
    }

    @Override
    public Page<T> getAll(int page, int limit) {
        return personRepository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public T getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public List<T> searchByName(String name) {
        return personRepository.findByFullNameContainingIgnoreCase(name);
    }

}
