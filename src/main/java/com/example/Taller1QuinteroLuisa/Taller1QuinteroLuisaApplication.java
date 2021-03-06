package com.example.Taller1QuinteroLuisa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.example.Taller1QuinteroLuisa.backend.model.person.UserApp;
import com.example.Taller1QuinteroLuisa.backend.model.person.UserType;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryHistoryRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.UserRepository;

@SpringBootApplication
@ComponentScan("com.example.Taller1QuinteroLuisa")
@EnableJpaRepositories(basePackages = "com.example.Taller1QuinteroLuisa")
public class Taller1QuinteroLuisaApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Taller1QuinteroLuisaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner add (UserRepository user, SalesPersonRepository personRepo,
			SalesPersonQuotaHistoryRepository personQuotaRepo, SalesTerritoryRepository territoryRepo,
			SalesTerritoryHistoryRepository territoryHistoryRepo) {
		return(args)->{
			UserApp u= new UserApp();
			u.setUsername("Fernanda");
			u.setPassword("{noop}Fer123456");
			u.setType(UserType.administrator);
			
			user.save(u);	
			
			UserApp operator= new UserApp();
			operator.setUsername("Xiao");
			operator.setPassword("{noop}Xiao123456");
			operator.setType(UserType.operator);
			user.save(operator);
		};
	}
}
