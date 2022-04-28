package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketBookingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);
	}


	// initializes database, disable on production
	@Override
	public void run(String... args) throws Exception {
		String command = "curl -X POST http://localhost:8080/resetDB ";
		Process process = Runtime.getRuntime().exec(command);
	}
}
