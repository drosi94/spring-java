package com.project.smdb.service.impl;

import com.project.smdb.domain.Director;
import com.project.smdb.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl extends PersonServiceImpl<Director> {
    public DirectorServiceImpl(DirectorRepository directorRepository) {
        super(directorRepository);
    }
}
