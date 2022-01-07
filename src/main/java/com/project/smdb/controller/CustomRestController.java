package com.project.smdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomRestController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello world";
    }
}