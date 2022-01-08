package com.project.smdb.service.impl;

import com.project.smdb.domain.Person;
import com.project.smdb.exception.ResourceNotFoundException;
import com.project.smdb.repository.PersonRepository;
import com.project.smdb.service.PersonService;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PersonServiceImpl<T extends Person> implements PersonService<T> {

    private final PersonRepository<T> personRepository;

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

        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        personRepository.delete(personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>((Collection<? extends T>) personRepository.findAll());
    }

    @Override
    public T getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public List<T> searchByName(String name) {
        return personRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

}
