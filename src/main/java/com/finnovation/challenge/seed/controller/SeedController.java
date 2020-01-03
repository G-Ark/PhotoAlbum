package com.finnovation.challenge.seed.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finnovation.challenge.seed.service.SeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeedController {
    @Autowired
    private SeedDataService seedDataService;

    @PostMapping("/api/v1/seed")
    public ResponseEntity<String> initiateSeedData() throws JsonProcessingException {
        seedDataService.seedData();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}