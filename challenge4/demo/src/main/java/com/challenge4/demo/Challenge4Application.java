package com.challenge4.demo;

import com.challenge4.demo.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.challenge4.demo")
public class Challenge4Application {


	public static void main(String[] args) {
		SpringApplication.run(Challenge4Application.class, args)
				.getBean(HomeController.class)
				.run();
	}

}
