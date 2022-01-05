package com.project.smdb.service;

import com.project.smdb.domain.Person;

import java.util.List;

public interface PersonService<T extends Person> {
    T create(T person);
    T update(Long id, T person);
    void delete(Long id);
    List<T> getAll();
    List<T> getByName(String name);
    T getById(Long id);
}
