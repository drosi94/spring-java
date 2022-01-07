package com.project.smdb.controller;

import com.project.smdb.domain.Actor;
import com.project.smdb.service.impl.ActorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("actors")
public class ActorController extends PersonController<Actor> {

    @Autowired
    public ActorController(ActorServiceImpl actorService) {
        super(actorService);
    }
}
