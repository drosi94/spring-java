package com.project.smdb.service.impl;

import com.project.smdb.domain.Producer;
import com.project.smdb.repository.ProducerRepository;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl extends PersonServiceImpl<Producer> {
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        super(producerRepository);
    }
}
