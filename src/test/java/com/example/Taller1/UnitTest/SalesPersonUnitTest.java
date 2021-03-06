package com.example.Taller1.UnitTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.test.context.ContextConfiguration;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.backend.model.hr.Employee;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.repository.EmployeeRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.backend.services.SalesPersonServiceImp;

@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
//@SpringBootTest()
class SalesPersonUnitTest {
	//private static final Integer BUSINESSENTITY_ID = 15ñ22215;
	private Salesperson person;
	private Employee employee;
	//private Salesterritory territory;

	@Mock
	private SalesPersonRepository salesPersonRepository;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private SalesTerritoryRepository salesTerritoryRepository;

	@InjectMocks
	private SalesPersonServiceImp salesPersonServiceImp;

	@BeforeAll
	static void init() {
		System.out.println("--------------- SALESPERSON TESTING -----------------");
		System.out.println(" ");
	}

	@Nested
	@DisplayName("Save methods for sales person")
	class savePerson {		
		@Test
		void saveSalesPersonCorrect() throws Exception {
			//Set up
			person= new Salesperson();
			employee= new Employee();

			LocalDate date  = LocalDate.parse("2022-11-10");
			person.setModifieddate(date);
			person.setSalesquota(new BigDecimal(152));
			person.setCommissionpct(BigDecimal.ZERO);
			person.setBonus(BigDecimal.ONE);

			Salesterritory territory= new Salesterritory();

			//when(employeeRepository.findById(BUSINESSENTITY_ID)).thenReturn(Optional.of(employee));
			when(salesTerritoryRepository.findById(57)).thenReturn(Optional.of(territory));
			when(salesPersonRepository.save(person)).thenReturn(person);

			//Method
			Salesperson temp= salesPersonServiceImp.save(person);

			//Asserts
			assertNotNull(temp);
			assertEquals(new BigDecimal(152), temp.getSalesquota());
			assertEquals(person.getBusinessentityid(), temp.getBusinessentityid());
			assertEquals(new BigDecimal(0), temp.getCommissionpct());
			assertEquals(territory, temp.getSalesterritory());

			verify(salesTerritoryRepository).findById(57);
			//verify(employeeRepository).findById(BUSINESSENTITY_ID);
			verify(salesPersonRepository).save(person);
		}
		
		@Test
		void salesPersonNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				salesPersonServiceImp.save(null);
			});
		}

		@Test
		void salesPersonWrongSalesQuota() throws Exception {
			person= new Salesperson();
			employee= new Employee();

			LocalDate date  = LocalDate.parse("2022-11-10");
			person.setModifieddate(date);
			try {
				person.setSalesquota(new BigDecimal(-152));
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> person.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
			}

			person.setCommissionpct(BigDecimal.ZERO);
			person.setBonus(BigDecimal.ONE);
		}

		@Test
		void salesPersonWrongSalesQuotaNull() throws Exception {
			person= new Salesperson();
			employee= new Employee();

			LocalDate date  = LocalDate.parse("2022-11-10");
			person.setModifieddate(date);
			person.setSalesquota(null);
			person.setCommissionpct(BigDecimal.ZERO);
			person.setBonus(BigDecimal.ONE);

			assertNull(person.getSalesquota());

			verify(salesPersonRepository, times(0)).save(person);
		}
		
		@Test
		void salesPersonWrongComissionNull() throws Exception {
			person= new Salesperson();
			employee= new Employee();

			LocalDate date  = LocalDate.parse("2022-11-10");
			person.setModifieddate(date);
			person.setSalesquota(new BigDecimal(42));
			person.setCommissionpct(null);
			person.setBonus(BigDecimal.ONE);

			assertNull(person.getCommissionpct());

			verify(salesPersonRepository, times(0)).save(person);
		}

		@Test
		void salesPersonPercentageWrong() throws Exception {
			person= new Salesperson();
			employee= new Employee();

			LocalDate date  = LocalDate.parse("2022-11-10");
			person.setModifieddate(date);
			person.setSalesquota(new BigDecimal(152));
			try {
				person.setCommissionpct(new BigDecimal(80));
			}catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> person.getSalesquota());
				assertEquals("El porcentaje de comision no esta entre 0 y 1", exception.getMessage());
			}
			person.setBonus(BigDecimal.ONE);

			//Salesterritory territory= new Salesterritory();
			Salesperson temp= salesPersonServiceImp.save(person);

			assertNull(temp);
			verify(salesPersonRepository, times(0)).save(person);
			//verify(salesTerritoryRepository, times(0)).findById(57);
		}
	}
	
	@Nested
	@DisplayName("Update methods for sales person")
	class updateSalesPerson{
		
		@Test
		void updateSalesPersonCorrect() throws Exception {
			person= new Salesperson();
			employee= new Employee();

			LocalDate date  = LocalDate.parse("2022-11-10");
			person.setModifieddate(date);
			person.setBusinessentityid(2215);
			person.setSalesquota(new BigDecimal(152));
			person.setCommissionpct(BigDecimal.ZERO);
			person.setBonus(BigDecimal.ONE);
			
			Salesperson person2= new Salesperson();

			Salesterritory territory= new Salesterritory();

			//when(employeeRepository.findById(BUSINESSENTITY_ID)).thenReturn(Optional.of(employee));
			when(salesTerritoryRepository.findById(57)).thenReturn(Optional.of(territory));
			when(salesPersonRepository.findById(2215)).thenReturn(Optional.of(person2));
			when(salesPersonRepository.save(person)).thenReturn(person);

			//Method
			Salesperson temp= salesPersonServiceImp.update(person);
			
			//Asserts
			assertNotNull(temp);
			assertEquals(new BigDecimal(152), temp.getSalesquota());
			assertEquals(person.getBusinessentityid(), temp.getBusinessentityid());
			assertEquals(new BigDecimal(0), temp.getCommissionpct());
			assertEquals(territory, temp.getSalesterritory());
		}
		
		@Test
		void salesPersonUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				salesPersonServiceImp.update(null);
			});
		}
	}

	/**@BeforeEach
	void setUp() {
		try {
			salesPersonServiceImp= new SalesPersonServiceImp(salesPersonRepository, salesTerritoryRepository, employeeRepository);
			person = new Salesperson();

			person.setBusinessentityid(BUSINESSENTITY_ID);
			person.setSalesquota(new BigDecimal(152));
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        Date date  = df.parse("10-11-2022");
	        long time1 = date.getTime();
	        Timestamp time = new Timestamp(time1);
	        person.setModifieddate(time);
	        person.setBonus(BigDecimal.ONE);
	        person.setCommissionpct(new BigDecimal(0.3));
	        employee.setBusinessentityid(BUSINESSENTITY_ID);
	        territory.setTerritoryid(57);	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	@AfterEach
	void tearDown() {
		person = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println("--------------- SALESPERSON TEST ENDED -----------------");
	}
}
