package com.prameswaradev.NotificationEmailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificationEmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationEmailServiceApplication.class, args);
	}

}
