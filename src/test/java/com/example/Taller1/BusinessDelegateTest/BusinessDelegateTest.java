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
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;

@SpringBootTest
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
public class BusinessDelegateTest {
	private final String URLPERSON= "http://localhost:8080/api/salesperson/";
	private final String URLPERSONQUOTA= "http://localhost:8080/api/salespersonquota/";
	private final String URLTERRITORY= "http://localhost:8080/api/salesterritory/";
	private final String URLTERRITORYHISTORY= "http://localhost:8080/api/salesterritoryhistory/";
	private final String URLCURRENCY= "http://localhost:8080/api/salesperson/";
	private final String URLCURRENCYRATE= "http://localhost:8080/api/salesperson/";
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	BusinessDelegate delegate;
	Salesperson person;
	Salesterritory territory;
	Salespersonquotahistory personquota;
	Salesterritoryhistory territoryhistory;
	Currency currency;
	Currencyrate currencyrate;
	
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
			assertEquals(delegate.getSalesterritoryHistory().size(),3);
		}
		
		@Test
		void addSalesTerritoryHistoryTest() {
			Salesterritoryhistory th= new Salesterritoryhistory();
			th.setSalesterritory(territory);
			
			when(restTemplate.postForObject(URLTERRITORYHISTORY, th, Salesterritoryhistory.class)).thenReturn(th);
			assertEquals(delegate.addTerritoryHistory(th).getId(), th.getId());
		}
		
		@Test
		void findByIdSalesTerritoryHistoryTest() {
			when(restTemplate.getForObject(URLTERRITORYHISTORY+territoryhistory.getId(), Salesterritoryhistory.class)).thenReturn(territoryhistory);	
			assertEquals(delegate.findByIdTerritoryHistory(territoryhistory.getId()).getId(), territoryhistory.getId());
		}
	}
	
	@Nested
	@DisplayName("Test methods for currency")
	class CurrencyTest{
		@BeforeEach
		void setUp(){
			currency= new Currency();
			currency.setCurrencycode(1);
			currency.setName("Dolares");
			
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
		}
		
		@Test
		void findAllCurrenciesTest() {
			Currency[] currencyList= new Currency[6];
			for (int i = 0; i < currencyList.length; i++) {
				Currency currency= new Currency();
				currencyList[i]= currency;
				delegate.addCurrency(currency);
			}
			when(restTemplate.getForObject(URLCURRENCY,Currency[].class)).thenReturn(currencyList);
			assertEquals(delegate.getCurrency().size(),6);
		}
		
		@Test
		void addCurrencyTest() {
			Currency c= new Currency();
			
			when(restTemplate.postForObject(URLCURRENCY, c, Currency.class)).thenReturn(c);
			assertEquals(delegate.addCurrency(c).getCurrencycode(), c.getCurrencycode());
		}
		
		@Test
		void findByIdCurrencyTest() {
			when(restTemplate.getForObject(URLCURRENCY+currency.getCurrencycode(), Currency.class)).thenReturn(currency);	
			assertEquals(delegate.findbyIdCurrency(currency.getCurrencycode()).getCurrencycode(), currency.getCurrencycode());
		}
	}
	
	@Nested
	@DisplayName("Test methods for currencyrate")
	class CurrencyRateTest{
		@BeforeEach
		void setUp() {
			currencyrate= new Currencyrate();
			currencyrate.setAveragerate(new BigDecimal(5));
			
			delegate= new BusinessDelegate();
			delegate.setRestTemplate(restTemplate);
		}
		
		@Test
		void findAllCurrencyRateTest(){
			Currencyrate[] currencyrateList= new Currencyrate[2];
			for (int i = 0; i < currencyrateList.length; i++) {
				Currencyrate currencyrate= new Currencyrate();
				currencyrateList[i]= currencyrate;
				delegate.addCurrencyrate(currencyrate);
			}
			when(restTemplate.getForObject(URLCURRENCYRATE,Currencyrate[].class)).thenReturn(currencyrateList);
			assertEquals(delegate.getCurrencyrate().size(),2);
		}

		@Test
		void addCurrencyRateTest() {
			Currencyrate cr= new Currencyrate();
			
			when(restTemplate.postForObject(URLCURRENCYRATE, cr, Currencyrate.class)).thenReturn(cr);
			assertEquals(delegate.addCurrencyrate(cr).getCurrencyrateid(), cr.getCurrencyrateid());
		}
		
		@Test
		void findByIdCurrencyRateTest() {
			when(restTemplate.getForObject(URLCURRENCYRATE+currencyrate.getCurrencyrateid(), Currencyrate.class)).thenReturn(currencyrate);	
			assertEquals(delegate.findbyIdCurrencyRate(currencyrate.getCurrencyrateid()).getCurrencyrateid(), currencyrate.getCurrencyrateid());
		}
	}
	
	@AfterAll
	static void end() {
		System.out.println(" ");
		System.out.println("--------------- BUSINESS DELEGATE TEST ENDED -----------------");
	}

}
