package com.example.Taller1.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryHistoryRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryHistoryServiceImp;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@SpringBootTest
public class SalesTerritoryHistoryIntegrationTest{
	private Salesterritoryhistory territoryHistory;
	private Salesperson person;
	private Salesterritory territory;
	private SalesTerritoryHistoryRepository territoryHistoryRepo;
	private SalesPersonRepository personRepo;
	private SalesTerritoryRepository territoryRepo;
	private SalesTerritoryHistoryServiceImp territoryService;
	
	private LocalDate date, date2;
	
	@Autowired
	public SalesTerritoryHistoryIntegrationTest(SalesTerritoryHistoryRepository territoryHistoryRepo,
			SalesPersonRepository personRepo, SalesTerritoryRepository territoryRepo,
			SalesTerritoryHistoryServiceImp territoryService) {
		
		this.territoryHistoryRepo= territoryHistoryRepo;
		this.personRepo = personRepo;
		this.territoryRepo = territoryRepo;
		this.territoryService = territoryService;
	}
	
	@BeforeAll
	static void init() {
		System.out.println("---------------SALESPERSON TESTED-----------------");
	}
	
	@Nested
	@DisplayName("Methods for save territory history")
	class saveTerritoryHistory{
		@BeforeEach
		void setUp() throws ParseException {
			territoryHistory= new Salesterritoryhistory();
			person= new Salesperson();
			territory= new Salesterritory();

			date= LocalDate.parse("2022-01-06");
			date2= LocalDate.parse("2022-04-14");
			
			territoryHistory.setId(152);
			territoryHistory.setModifieddate(date);
			territoryHistory.setEnddate(date2);
			
			territoryRepo.save(territory);
			territoryHistory.setSalesterritory(territory);
			
			personRepo.save(person);
			territoryHistory.setSalesperson(person);
		}
		
		@Test
		void saveCorrectly() throws Exception {
			Salesterritory temp= new Salesterritory();
			temp.setTerritoryid(57);
			territoryHistory.setSalesterritory(temp);
			
			Salesperson aux = new Salesperson();
			aux.setBusinessentityid(2215);
			territoryHistory.setSalesperson(person);
			
			territoryService.save(territoryHistory);
			assertNotNull(territoryService);
		}
		
		@Test
		void salesPersonNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				territoryService.save(null);
			});
		}
		
		@Test
		@DisplayName("Saving a date bigger than the end one")
		void wrongInitialDate() throws ParseException {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			date= LocalDate.parse("2022-01-06");
			
			try {
				territoryHistory.setModifieddate(date);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getModifieddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
		}
		
		@Test
		@DisplayName("Saving a end date smaller than the initial one")
		void wrongEndDate() throws ParseException {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			date= LocalDate.parse("2021-01-01");

			try {
				territoryHistory.setEnddate(date);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getEnddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
		}
	}
	
	@Nested
	@DisplayName("Methods for update territory history")
	class updateTerritoryHistory{
		@BeforeEach
		void setUp() throws ParseException {
			territoryHistory= new Salesterritoryhistory();
			person= new Salesperson();
			territory= new Salesterritory();

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			date= LocalDate.parse("2022-01-06");
			date2= LocalDate.parse("2022-04-14");
			
			territoryHistory.setId(152);
			territoryHistory.setModifieddate(date);
			territoryHistory.setEnddate(date2);
			
			territoryRepo.save(territory);
			territoryHistory.setSalesterritory(territory);
			
			personRepo.save(person);
			territoryHistory.setSalesperson(person);
		}
		
		@Test
		void updateCorrectly() throws Exception {
			Salesterritoryhistory t2= new Salesterritoryhistory();
			Salesperson person2= new Salesperson();
			Salesterritory temp= new Salesterritory();
			temp.setTerritoryid(57);
			person2.setBusinessentityid(2215);
			t2.setSalesterritory(temp);
			t2.setSalesperson(person2);
			territoryService.update(territoryHistory);
		}
		
		@Test
		void salesTerritoryUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				territoryService.update(null);
			});
		}		
	}
	
	@AfterEach
	void tearDown() {
		territoryHistory = null;
		person= null;
		territory= null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println("--------------- FINISHED -----------------");
	}

}
