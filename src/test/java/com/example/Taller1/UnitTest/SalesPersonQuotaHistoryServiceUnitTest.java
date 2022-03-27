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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.repository.BusinessentityRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonQuotaHistoryServiceImp;

@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
public class SalesPersonQuotaHistoryServiceUnitTest {
	private Salespersonquotahistory personQuota;
	private Salespersonquotahistory p2;
	private Salesperson person;
	//private Businessentity business;
	
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
		System.out.println("--------------- SALESPERSONQUOTA TESTING -----------------");
		System.out.println(" ");
	}
	
	@Nested
	@DisplayName("Save methods for sales person quota history")
	class savePersonQuota{
		@BeforeEach
		void setUp() throws Exception{
			personQuota= new Salespersonquotahistory();
			person= new Salesperson();
			//business= new Businessentity();

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("06-01-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			personQuota.setModifieddate(time);
			personQuota.setSalesquota(new BigDecimal(122));			
		}
		
		@Test
		@DisplayName("Save a null person quota")
		void salesPersonQuotaNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				personQuotaService.save(null, null);
			});
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
			assertEquals(personQuota.getBusinessentityid(), temp.getBusinessentityid());
			
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
		@DisplayName("Saving a person quota with a null quota")
		void nullQuota() {
			try {
				personQuota.setSalesquota(null);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
				assertNull(personQuota.getSalesquota());
			}
			
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
		
		@Test
		@DisplayName("Saving a date bigger than the current one")
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
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
		
		@Test
		@DisplayName("Saving a null date")
		void nullDate() {			
			try {
				personQuota.setModifieddate(null);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getModifieddate());
				assertEquals("La fecha de inicio no es menor a la fecha actual", exception.getMessage());
				assertNull(personQuota.getModifieddate());
			}
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
	}
	
	@Nested
	@DisplayName("Update methods for sales person quota")
	class updatePersonQuota{
		
		@BeforeEach
		void setUp() throws Exception {
			personQuota= new Salespersonquotahistory();
			person= new Salesperson();

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("06-01-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			personQuota.setBusinessentityid(22);
			personQuota.setModifieddate(time);
			personQuota.setSalesquota(new BigDecimal(122));
			p2= new Salespersonquotahistory();
		}
		
		@Test
		@DisplayName("Updating a person quota correctly")
		void salesPersonQuotaUpdateCorrect() throws Exception {
			when(personRepo.findById(152)).thenReturn(Optional.of(person));
			when(personQuotaRepo.findById(22)).thenReturn(Optional.of(p2));
			when(personQuotaRepo.save(personQuota)).thenReturn(personQuota);
			
			Salespersonquotahistory temp = personQuotaService.update(personQuota, 152);
			assertNotNull(temp);
			assertEquals(new BigDecimal(122), temp.getSalesquota());
			assertEquals(personQuota.getBusinessentityid(), temp.getBusinessentityid());
			
			verify(personRepo).findById(152);
			verify(personQuotaRepo, times(1)).save(personQuota);
		}
		
		@Test
		@DisplayName("Updating a person quota to null")
		void salesPersonQuotaUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				personQuotaService.update(null, null);
			});
		}
		
		@Test
		@DisplayName("Updating a person quota date to null")
		void salesPersonQuotaDateUpdateNull() throws Exception{
			Salespersonquotahistory temp= null;
			try {
				personQuota.setModifieddate(null);
				temp= personQuotaService.update(personQuota, 152);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La fecha de inicio no es menor a la fecha actual", exception.getMessage());
				assertNull(personQuota.getModifieddate());
				assertNull(temp);
			}
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
		
		@Test
		@DisplayName("Updating a person quota with a null quota")
		void UpdateNullQuota() throws Exception {
			Salespersonquotahistory temp= null;
			try {
				personQuota.setSalesquota(null);
				temp= personQuotaService.update(personQuota, 152);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
				assertNull(personQuota.getSalesquota());
				assertNull(temp);
			}
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
		
		@Test
		@DisplayName("Updating a date bigger than the current one")
		void UpdatewrongDate() throws Exception {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			Salespersonquotahistory temp= null;
			
			try {
				personQuota.setModifieddate(time);
				temp= personQuotaService.update(personQuota, 152);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La fecha de inicio no es menor a la fecha actual", exception.getMessage());
				assertNull(temp);
			}
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
		
		@Test
		@DisplayName("Updating a person quota with a wrong quota")
		void UpdatewrongQuota() throws Exception {
			Salespersonquotahistory temp= null;
			try {
				personQuota.setSalesquota(new BigDecimal(-5));
				temp= personQuotaService.update(personQuota, 152);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> personQuota.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
				assertNull(temp);
			}
			verify(personQuotaRepo, times(0)).save(personQuota);
		}
	}
	
	@AfterEach
	void tearDown() {
		person = null;
		personQuota= null;
		System.gc();
	}
	
	@AfterAll
	static void end() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSONQUOTA TEST ENDED -----------------");
	}

}
