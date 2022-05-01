package com.example.Taller1QuinteroLuisa;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.Taller1QuinteroLuisa.model.person.UserApp;
import com.example.Taller1QuinteroLuisa.model.person.UserType;
import com.example.Taller1QuinteroLuisa.services.UserServiceImp;

@SpringBootApplication
public class Taller1QuinteroLuisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taller1QuinteroLuisaApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(Taller1QuinteroLuisaApplication.class, args);
		UserServiceImp user= context.getBean(UserServiceImp.class);
		
		UserApp u= new UserApp();
		u.setUsername("Fernanda");
		u.setPassword("{noop}Fer123456");
		u.setType(UserType.ADMINISTRATOR);
		
		user.save(u);
	}
	
//	@Bean
//	public CommandLineRunner add () {
//		return(args)->{
//			
//		};
//	}
}
