package com.zendesk.marcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner{



	@Autowired
	TicketManager ticketManager;

	public static void main(String[] args) {
		SpringApplication.run(ConsoleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ticketManager.start();
		
	}

}
