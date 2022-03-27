package com.example.Taller1.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.model.person.Businessentity;
import com.example.Taller1QuinteroLuisa.model.person.Countryregion;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.BusinessentityRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonQuotaHistoryServiceImp;

@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
public class SalesPersonQuotaHistoryServiceUnitTest {
	private Salespersonquotahistory personQuota;
	private Salesperson person;
	private Businessentity business;
	
	@Mock
	private SalesPersonQuotaHistoryRepository personQuotaRepo;
	
	@Mock
	private SalesPersonRepository personRepo;
	
	@Mock
	private BusinessentityRepository businessRepo;
	
	@InjectMocks
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	
	@BeforeAll
	static void init() {
		System.out.println("--------------- SALESTERRITORY TESTING -----------------");
		System.out.println(" ");
	}
	
	@Nested
	@DisplayName("Save methods for sales person quota history")
	class savePersonQuota{
		@BeforeEach
		void setUp() throws Exception{
			personQuota= new Salespersonquotahistory();
			person= new Salesperson();
			business= new Businessentity();

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("06-01-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			personQuota.setModifieddate(time);
			personQuota.setSalesquota(new BigDecimal(122));			
		}
		
		@Test
		@DisplayName("Save Test Correctly")
		void savePersonQuotaCorrect() throws Exception {
			when(personRepo.findById(152)).thenReturn(Optional.of(person));
			when(personQuotaRepo.save(personQuota)).thenReturn(personQuota);

			//Method
			Salespersonquotahistory temp = personQuotaService.save(personQuota, 152);

			//Asserts
			assertNotNull(temp);
			assertEquals(new BigDecimal(122), temp.getSalesquota());
			assertEquals(personQuota.getId(), temp.getId());
			
			verify(personRepo).findById(152);
			verify(personQuotaRepo).save(personQuota);
			verify(personQuotaRepo, times(1)).save(personQuota);
		}
		
		@Test
		@DisplayName("Saving a person quota with a wrong quota")
		void wrongQuota() {
			try {
				personQuota.setSalesquota(new BigDecimal(-5));
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
			}
			
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
		
		@Test
		void nullQuota() {
			try {
				personQuota.setSalesquota(null);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
			}
			
			verify(personQuotaRepo, times(0)).save(personQuota);
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
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
			}
			
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
		
		
	}
	
	@AfterAll
	static void end() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSON TEST ENDED -----------------");
	}

}
