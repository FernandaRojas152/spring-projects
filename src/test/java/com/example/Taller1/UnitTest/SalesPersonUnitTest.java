package com.example.Taller1.UnitTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.model.hr.Employee;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.EmployeeRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonService;
import com.example.Taller1QuinteroLuisa.services.SalesPersonServiceImp;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
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
	private SalesPersonService salesPersonService;
	
	@BeforeAll
    static void init() {
        System.out.println("--------------- SALESPERSON TESTING -----------------");
    }
	
	@BeforeEach
	void setUp() {
		try {
			salesPersonService= new SalesPersonServiceImp(salesPersonRepository, salesTerritoryRepository, employeeRepository);
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
			
	        employee.setBusinessentityid(BUSINESSENTITY_ID);
	        territory.setTerritoryid(57);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(1)
	void saveSalesPersonTest() {
		
	}
	
	

}
