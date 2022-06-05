package com.example.Taller1.BusinessDelegateTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.frontend.businessdelegate.BusinessDelegate;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;

@SpringBootTest
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
public class BusinessDelegateTest {
	private final String URLPERSON= "http://localhost:8080/api/salespersonRest/list";
	private final String URLPERSONQUOTA= "http://localhost:8080/api/salespersonquotaRest/list";
	private final String URLTERRITORY= "http://localhost:8080/api/salesterritoryRest/list";
	private final String URLTERRITORYHISTORY= "http://localhost:8080/api/salesterritoryhistoryRest/list";
	private final String URLCURRENCY= "http://localhost:8080/api/salespersonRest/list";
	private final String URLCURRENCYRATE= "http://localhost:8080/api/salespersonRest/list";
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	BusinessDelegate delegate;
	Salesperson person;
	
	@BeforeAll
	static void init() {
		System.out.println("--------------- BUSINESS DELEGATE TESTING -----------------");
		System.out.println(" ");
	}
	
	@Nested
	@DisplayName("Test methods for sales person")
	class SalespersonTests{
		@BeforeEach
		void setUp() {
			MockitoAnnotations.initMocks(this);
			person= new Salesperson();
			person.setBusinessentityid(1);
			person.setCommissionpct(new BigDecimal(1));
			person.setSalesquota(new BigDecimal(30));
			
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
			delegate.addSalesperson(person);
		}
		
		@Test
		void findAllSalesPerson() {
			Salesperson[] personList= new Salesperson[4];
			for (int i = 0; i < personList.length; i++) {
				Salesperson person= new Salesperson();
				personList[i]= person;
				delegate.addSalesperson(person);
			}
			
			when(restTemplate.getForObject(URLPERSON,Salesperson[].class)).thenReturn(personList);
			
			assertEquals(delegate.getSalesPerson().size(),4);
		}
		
	}

}
