package com.project.smdb.repository;

import com.project.smdb.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends CrudRepository<T, Long> {
    List<T> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String name, String name2);
}