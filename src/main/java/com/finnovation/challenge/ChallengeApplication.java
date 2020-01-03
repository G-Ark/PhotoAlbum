package com.finnovation.challenge;

import com.finnovation.challenge.seed.service.SeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {
	@Autowired
	private SeedDataService seedDataService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
}
