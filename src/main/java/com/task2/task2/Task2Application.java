package com.task2.task2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // Enable scheduling in the application
public class Task2Application {
	public static void main(String[] args) {
		SpringApplication.run(Task2Application.class, args);
	}
}
