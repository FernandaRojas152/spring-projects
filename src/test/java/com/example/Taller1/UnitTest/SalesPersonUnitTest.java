package com.example.Taller1.UnitTest;

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
import com.example.Taller1QuinteroLuisa.model.hr.Employee;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.EmployeeRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonServiceImp;

@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
//@SpringBootTest()
class SalesPersonUnitTest {
	private static final Integer BUSINESSENTITY_ID = 1522215;
	
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
    }
	
	@Nested
	@DisplayName("Save methods for sales person")
	class savePerson {
		
		@Test
		void saveSalesPersonCorrect() throws Exception {
			//Set up
			
			person= new Salesperson();
			employee= new Employee();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        Date date  = df.parse("10-11-2022");
	        long time1 = date.getTime();
	        Timestamp time = new Timestamp(time1);
	        person.setModifieddate(time);
	        person.setSalesquota(new BigDecimal(152));
	        person.setCommissionpct(BigDecimal.ZERO);
	        person.setBonus(BigDecimal.ONE);
	        
	        Salesterritory territory= new Salesterritory();
	        
			//when(employeeRepository.findById(BUSINESSENTITY_ID)).thenReturn(Optional.of(employee));
			when(salesTerritoryRepository.findById(57)).thenReturn(Optional.of(territory));
			when(salesPersonRepository.save(person)).thenReturn(person);
			
			//Method
			
			Salesperson temp= salesPersonServiceImp.save(person,57);
			
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
		void salesPersonWrongSalesQuota() throws Exception {
			person= new Salesperson();
			employee= new Employee();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        Date date  = df.parse("10-11-2022");
	        long time1 = date.getTime();
	        Timestamp time = new Timestamp(time1);
	        person.setModifieddate(time);
	        try {
	        	person.setSalesquota(new BigDecimal(-152));
			} catch (Exception e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> person.getSalesquota());
		        assertEquals("La cuota no es mayor que 0", exception.getMessage());
			}
	        
	        person.setCommissionpct(BigDecimal.ZERO);
	        person.setBonus(BigDecimal.ONE);
	        
	        //Salesperson temp= salesPersonServiceImp.save(person,57);
	        
	        //assertNull(temp);
	        
	        
	        
		}
		
		@Test
		void salesPersonWrongSalesQuotaNull() throws Exception {
			person= new Salesperson();
			employee= new Employee();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        Date date  = df.parse("10-11-2022");
	        long time1 = date.getTime();
	        Timestamp time = new Timestamp(time1);
	        person.setModifieddate(time);
	        person.setSalesquota(null);
	        person.setCommissionpct(BigDecimal.ZERO);
	        person.setBonus(BigDecimal.ONE);
	        
	        assertNull(person.getSalesquota());
	        
	        verify(salesPersonRepository, times(0)).save(person);
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
	}
	*/
	
	
	void tearDown() {
		person = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println("--------------- TEST ENDED -----------------");
	}
}
