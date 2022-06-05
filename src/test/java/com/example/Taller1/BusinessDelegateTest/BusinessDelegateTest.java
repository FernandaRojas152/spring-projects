package com.example.Taller1.BusinessDelegateTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
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
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.frontend.businessdelegate.BusinessDelegate;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;

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
	Salesterritory territory;
	Salespersonquotahistory personquota;
	Salesterritoryhistory territoryhistory;
	
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
			person= new Salesperson();
			person.setBusinessentityid(1);
			person.setCommissionpct(new BigDecimal(1));
			person.setSalesquota(new BigDecimal(30));
			
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
//			delegate.addSalesperson(person);
		}
		
		@Test
		void findAllSalesPersonTest() {
			Salesperson[] personList= new Salesperson[4];
			for (int i = 0; i < personList.length; i++) {
				Salesperson person= new Salesperson();
				personList[i]= person;
				delegate.addSalesperson(person);
			}
			
			when(restTemplate.getForObject(URLPERSON,Salesperson[].class)).thenReturn(personList);
			
			assertEquals(delegate.getSalesPerson().size(),4);
		}
		
		@Test
		void addSalesPersonTest() {
			Salesperson p= new Salesperson();
			p.setBusinessentityid(2);
			p.setCommissionpct(new BigDecimal(0.6));
			p.setSalesquota(new BigDecimal(300));
			
			when(restTemplate.postForObject(URLPERSON, p, Salesperson.class)).thenReturn(p);
			assertEquals(delegate.addSalesperson(p).getBusinessentityid(), p.getBusinessentityid());
		}
		
		@Test
		void findByIdSalesPersonTest() {
			when(restTemplate.getForObject(URLPERSON+person.getBusinessentityid(), Salesperson.class)).thenReturn(person);	
			assertEquals(delegate.findByIdSalesperson(person.getBusinessentityid()).getBusinessentityid(), person.getBusinessentityid());
		}
		
		/**
		 * !!
		 * NEEDS TO BE FIXED !!
		 * WHEN FIXED, IMPLEMENT IT ON ALL TESTS
		 * !!
		 */
//		@Test
//		void updateSalesPersonTest(){
//			delegate.addSalesperson(person);
//			person.setCommissionpct(new BigDecimal(0.6));
//			delegate.updateSalesperson(person);
//			
//			verify(restTemplate).put(URLPERSON, person, Salesperson.class);
//		}
//		
//		@Test
//		void deleteSalesPersonTest() {
//			Salesperson p= new Salesperson();
//			p.setBusinessentityid(2);
//			p.setCommissionpct(new BigDecimal(0.6));
//			p.setSalesquota(new BigDecimal(300));
//			delegate.addSalesperson(p);
//			
//			delegate.deleteSalesperson(p.getBusinessentityid());
//			verify(restTemplate).put(URLPERSON+p.getBusinessentityid(), p, Salesperson.class);
//		}	
	}
	
	@Nested
	@DisplayName("Test methods for sales territory")
	class SalesTerritoryTest{
		@BeforeEach
		void setUp() {
			territory= new Salesterritory();
			territory.setTerritoryid(1);
			territory.setCountryregioncode("COL");
			territory.setName("Colombia");
			
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
		}
		
		@Test
		void findAllTerritoriesTest(){
			Salesterritory[] territoryList= new Salesterritory[2];
			for (int i = 0; i < territoryList.length; i++) {
				Salesterritory territory= new Salesterritory();
				territoryList[i]= territory;
				delegate.addSalesterritory(territory);
			}
			when(restTemplate.getForObject(URLTERRITORY,Salesterritory[].class)).thenReturn(territoryList);
			assertEquals(delegate.getSalesterritory().size(),2);
		}
		
		@Test
		void addSalesTerritoryTest(){
			Salesterritory t= new Salesterritory();
			territory.setTerritoryid(1);
			territory.setCountryregioncode("COL");
			territory.setName("Colombia");
			
			when(restTemplate.postForObject(URLTERRITORY, t, Salesterritory.class)).thenReturn(t);
			assertEquals(delegate.addSalesterritory(t).getTerritoryid(), t.getTerritoryid());
		}
		
		@Test
		void findByIdSalesTerritoryTest(){
			when(restTemplate.getForObject(URLTERRITORY+territory.getTerritoryid(), Salesterritory.class)).thenReturn(territory);	
			assertEquals(delegate.findByIdTerritory(territory.getTerritoryid()).getTerritoryid(), territory.getTerritoryid());
		}
	}
	
	@Nested
	@DisplayName("Test methods for sales personquota")
	class SalesPersonQuotaTest{
		@BeforeEach
		void setUp() {
			person= new Salesperson();
			person.setBusinessentityid(1);
			person.setCommissionpct(new BigDecimal(1));
			person.setSalesquota(new BigDecimal(30));
			
			personquota= new Salespersonquotahistory();
			personquota.setSalesquota(new BigDecimal(40));
			personquota.setSalesperson(person);
			
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
		}
		
		@Test
		void findAllPersonQuotaTest() {
			Salespersonquotahistory[] personquotaList= new Salespersonquotahistory[10];
			for (int i = 0; i < personquotaList.length; i++) {
				Salespersonquotahistory personquota= new Salespersonquotahistory();
				personquotaList[i]= personquota;
				delegate.addPersonQuota(personquota);
			}
			when(restTemplate.getForObject(URLPERSONQUOTA,Salespersonquotahistory[].class)).thenReturn(personquotaList);
			assertEquals(delegate.getSalespersonQuota().size(),10);
		}
		
		@Test
		void addSalesPersonQuotaTest(){
			Salespersonquotahistory pq= new Salespersonquotahistory();
			personquota.setSalesquota(new BigDecimal(66));
			personquota.setSalesperson(person);
			
			when(restTemplate.postForObject(URLPERSONQUOTA, pq, Salespersonquotahistory.class)).thenReturn(pq);
			assertEquals(delegate.addPersonQuota(pq).getBusinessentityid(), pq.getBusinessentityid());
		}
		
		@Test
		void findByIdPersonQuotaTest() {
			when(restTemplate.getForObject(URLPERSONQUOTA+personquota.getBusinessentityid(), Salespersonquotahistory.class)).thenReturn(personquota);	
			assertEquals(delegate.findByIdPersonQuota(personquota.getBusinessentityid()).getBusinessentityid(), personquota.getBusinessentityid());
		}
	}
	
	@Nested
	@DisplayName("Test methods for salesterritory history")
	class SalesTerritoryHistoryTest{
		@BeforeEach
		void setUp() {
			territoryhistory= new Salesterritoryhistory();
			territory= new Salesterritory();
			territory.setTerritoryid(1);
			territory.setCountryregioncode("COL");
			territory.setName("Colombia");
			
			territoryhistory.setSalesterritory(territory);
			
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
		}
		
		@Test
		void findAllSalesTerritoryHistoryTest() {
			Salesterritoryhistory[] territoryhistoryList= new Salesterritoryhistory[3];
			for (int i = 0; i < territoryhistoryList.length; i++) {
				Salesterritoryhistory territoryhistory= new Salesterritoryhistory();
				territoryhistoryList[i]= territoryhistory;
				delegate.addTerritoryHistory(territoryhistory);
			}
			when(restTemplate.getForObject(URLTERRITORYHISTORY,Salesterritoryhistory[].class)).thenReturn(territoryhistoryList);
			assertEquals(delegate.getSalesterritoryHistory().size(),10);
		}
		
		@Test
		void addSalesTerritoryHistoryTest() {
			Salesterritoryhistory th= new Salesterritoryhistory();
			th.setSalesterritory(territory);
			
			when(restTemplate.postForObject(URLTERRITORYHISTORY, th, Salesterritoryhistory.class)).thenReturn(th);
			assertEquals(delegate.addTerritoryHistory(th).getId(), th.getId());
		}
	}
	
	@AfterAll
	static void end() {
		System.out.println(" ");
		System.out.println("--------------- BUSINESS DELEGATE TEST ENDED -----------------");
	}

}
