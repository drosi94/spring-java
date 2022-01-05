package com.project.smdb.controller;

import com.project.smdb.domain.Producer;
import com.project.smdb.service.impl.ProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producers")
public class ProducerController extends PersonController<Producer>{

    @Autowired
    public ProducerController(ProducerServiceImpl producerService) {
        super(producerService);
    }
}
