package com.project.smdb.service.impl;

import com.project.smdb.domain.Actor;
import com.project.smdb.repository.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends PersonServiceImpl<Actor> {
    public ActorServiceImpl(ActorRepository actorRepository) {
        super(actorRepository);
    }
}
