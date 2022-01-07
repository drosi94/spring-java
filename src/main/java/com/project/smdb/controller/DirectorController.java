package com.project.smdb.controller;

import com.project.smdb.domain.Director;
import com.project.smdb.service.impl.DirectorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("directors")
public class DirectorController extends PersonController<Director> {

    @Autowired
    public DirectorController(DirectorServiceImpl directorService) {
        super(directorService);
    }
}
