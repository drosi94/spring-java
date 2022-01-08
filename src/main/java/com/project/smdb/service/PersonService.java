package com.project.smdb.service;

import com.project.smdb.domain.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService<T extends Person> {
    T create(T person);
    T update(Long id, T person);
    void delete(Long id);
    Page<T> getAll(int page, int limit);
    T getById(Long id);
    List<T> searchByName(String name);
}
