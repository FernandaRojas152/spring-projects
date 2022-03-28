package com.example.Taller1.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonQuotaHistoryServiceImp;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@SpringBootTest
public class SalesPersonQuotaHistoryIntegrationTest {
	private Salespersonquotahistory personQuota;
	private Salesperson person;
	
	private SalesPersonQuotaHistoryRepository personQuotaRepo;
	private SalesPersonRepository personRepo;
	
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	
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

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("06-01-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			personQuota.setBusinessentityid(2215);
			personQuota.setModifieddate(time);
			personQuota.setSalesquota(new BigDecimal(122));
			
			personRepo.save(person);
			personQuota.setSalesperson(person);
		}
		
		@Test
		void saveCorrectly() throws Exception {
			Salesperson temp = new Salesperson();
			temp.setBusinessentityid(152);
			personQuota.setSalesperson(temp);
			personQuotaService.save(personQuota, 152);
			assertNotNull(temp);
			assertNotNull(personQuotaService);
		}
		
		@Test
		void salesPersonNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				personQuotaService.save(null, null);
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
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			try {
				personQuota.setModifieddate(time);
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

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("06-01-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			personQuota.setBusinessentityid(2215);
			personQuota.setModifieddate(time);
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
			personQuotaService.update(personQuota, 152);
			assertNotNull(personQuotaService);
		}
		
		@Test
		void salesPersonUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				personQuotaService.update(null, null);
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
