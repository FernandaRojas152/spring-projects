package com.example.Taller1.UnitTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest()
//@ExtendWith(MockitoExtension.class)
class SalesPersonUnitTest {
	private static final Integer BUSINESSENTITY_ID = 1522215;
	
	private Salesperson person;
	private Employee employee;
	private Salesterritory territory;
	
	@Mock
	private SalesPersonRepository salesPersonRepository;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private SalesTerritoryRepository salesTerritoryRepository;
	
	@InjectMocks
	private SalesPersonServiceImp salesPersonServiceImp;
	
	@Autowired
	public SalesPersonUnitTest(SalesPersonRepository salesPersonRepository, EmployeeRepository employeeRepository, SalesTerritoryRepository salesTerritoryRepository) {		
		this.salesPersonServiceImp = new SalesPersonServiceImp(salesPersonRepository, salesTerritoryRepository, employeeRepository);
	}
	
	@BeforeAll
    static void init() {
        System.out.println("--------------- SALESPERSON TESTING -----------------");
    }
	
	@BeforeEach
	void setUp() {
		try {
			salesPersonServiceImp= new SalesPersonServiceImp(salesPersonRepository, salesTerritoryRepository, employeeRepository);
			person = new Salesperson();
			employee= new Employee();
			territory= new Salesterritory();
			
			person.setBusinessentityid(BUSINESSENTITY_ID);
			person.setSalesquota(new BigDecimal(152));
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        Date date  = df.parse("10-11-2022");
	        long time1 = date.getTime();
	        Timestamp time = new Timestamp(time1);
	        person.setModifieddate(time);
	        person.setBonus(BigDecimal.ONE);
	        person.setCommissionpct(BigDecimal.valueOf(0.3));
			
	        employee.setBusinessentityid(BUSINESSENTITY_ID);
	        territory.setTerritoryid(57);	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void saveSalesPersonTest() {
		when(salesPersonRepository.findById(BUSINESSENTITY_ID)).thenReturn(Optional.of(person));
		when(employeeRepository.findById(BUSINESSENTITY_ID)).thenReturn(Optional.of(employee));
		when(salesTerritoryRepository.findById(57)).thenReturn(Optional.of(territory));
		
		try {
			salesPersonServiceImp.save(person);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Salesperson temp= salesPersonRepository.findById(BUSINESSENTITY_ID).get();
		
		assertEquals(new BigDecimal(152), temp.getSalesquota());
		assertEquals(temp.getBusinessentityid(), employee.getBusinessentityid());
		
	}
	
	@AfterEach
	void tearDown() {
		person = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println("--------------- TEST ENDED -----------------");
	}
}
