package com.challenge6.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Challenge6Application {

	public static void main(String[] args) {
		SpringApplication.run(
				Challenge6Application.class, args);
	}
}
