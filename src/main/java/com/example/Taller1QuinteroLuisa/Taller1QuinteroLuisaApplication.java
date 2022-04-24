package com.example.Taller1QuinteroLuisa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Taller1QuinteroLuisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taller1QuinteroLuisaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner add () {
		return(args)->{
			
		};
	}
}
