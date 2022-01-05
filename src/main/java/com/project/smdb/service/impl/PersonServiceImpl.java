package com.project.smdb.service.impl;

import com.project.smdb.domain.Person;
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
        return null;
    }

    @Override
    public T update(Long id, T person) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>((Collection<? extends T>) personRepository.findAll());
    }

    @Override
    public List<T> getByName(String name) {
        return null;
    }

    @Override
    public T getById(Long id) {
        return null;
    }
}
