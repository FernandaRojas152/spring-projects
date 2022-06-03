package com.example.Taller1.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.text.ParseException;
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
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonQuotaHistoryServiceImp;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@SpringBootTest
public class SalesPersonQuotaHistoryIntegrationTest {
	private Salespersonquotahistory personQuota;
	private Salesperson person;
	private SalesPersonQuotaHistoryRepository personQuotaRepo;
	private SalesPersonRepository personRepo;
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	
	private LocalDate date;
	
	@Autowired
	public SalesPersonQuotaHistoryIntegrationTest(SalesPersonQuotaHistoryRepository personQuotaRepo,
			SalesPersonRepository personRepo, SalesPersonQuotaHistoryServiceImp personQuotaService) {
		this.personQuotaRepo = personQuotaRepo;
		this.personRepo = personRepo;
		this.personQuotaService = personQuotaService;
	}
	
	
	@BeforeAll
	static void init() {
		System.out.println("---------------SALESPERSON TESTED-----------------");
	}
	
	@Nested
	@DisplayName("Methods for save a person quota")
	class savePersonQuota {
		@BeforeEach
		void setUp() throws Exception{
			personQuota= new Salespersonquotahistory();
			person= new Salesperson();

			personQuota.setBusinessentityid(2215);
			date= LocalDate.parse("2022-01-06");
			personQuota.setModifieddate(date);
			personQuota.setSalesquota(new BigDecimal(122));
			
			personRepo.save(person);
			personQuota.setSalesperson(person);
		}
		
		@Test
		void saveCorrectly() throws Exception {
			Salesperson temp = new Salesperson();
			temp.setBusinessentityid(152);
			personQuota.setSalesperson(temp);
			personQuotaService.save(personQuota);
			assertNotNull(temp);
			assertNotNull(personQuotaService);
		}
		
		@Test
		void salesPersonNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				personQuotaService.save(null);
			});
		}
		
		@Test
		void wrongQuota() {
			try {
				personQuota.setSalesquota(new BigDecimal(-5));
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
			}
		}
		
		@Test
		void wrongDate() throws ParseException {
			date= LocalDate.parse("2022-04-14");
			
			try {
				personQuota.setModifieddate(date);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getModifieddate());
				assertEquals("La fecha de inicio no es menor a la fecha actual", exception.getMessage());
			}
		}	
	}
	
	@Nested
	@DisplayName("Methods for updating a person quota")
	class updatePersonQuota{
		@BeforeEach
		void setUp() throws Exception{
			personQuota= new Salespersonquotahistory();
			person= new Salesperson();

			date= LocalDate.parse("2022-01-06");
			personQuota.setBusinessentityid(2215);
			personQuota.setModifieddate(date);
			personQuota.setSalesquota(new BigDecimal(122));
			
			personRepo.save(person);
			personQuota.setSalesperson(person);
		}
		
		@Test
		void updateCorrectly() throws Exception {
			Salespersonquotahistory p2= new Salespersonquotahistory();
			Salesperson temp= new Salesperson();
			temp.setBusinessentityid(152);
			p2.setSalesperson(temp);
			personQuotaService.update(personQuota);
			assertNotNull(personQuotaService);
		}
		
		@Test
		void salesPersonUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				personQuotaService.update(null);
			});
		}
	}
	
	@AfterEach
	void tearDown() {
		//s = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println("--------------- FINISHED -----------------");
	}


}
