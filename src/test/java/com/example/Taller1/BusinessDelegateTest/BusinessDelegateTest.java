package com.example.Taller1.BusinessDelegateTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.frontend.businessdelegate.BusinessDelegate;

@SpringBootTest
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(SpringExtension.class)
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
	private BusinessDelegate delegate;
	
	@BeforeAll
	static void init() {
		System.out.println("--------------- BUSINESS DELEGATE TESTING -----------------");
		System.out.println(" ");
	}
	
	@Nested
	@DisplayName("Test methods for sales person")
	class Salesperson{
		@BeforeEach
		void setUp() {
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
		}
		
		@Test
		void findAllSalesPerson() {
			Salesperson[] personList= new Salesperson[4];
			for (int i = 0; i < personList.length; i++) {
				Salesperson person= new Salesperson();
				personList[i]= person;
			}
			
			when(restTemplate.getForObject(URLPERSON,Salesperson[].class)).thenReturn(personList);
			
			assertEquals(delegate.getSalesPerson().size(),4);
		}
		
	}

}
