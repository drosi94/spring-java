package com.project.smdb.repository;

import com.project.smdb.domain.Person;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends PagingAndSortingRepository<T, Long> {
    List<T> findByFullNameContainingIgnoreCase(String fullName);
}