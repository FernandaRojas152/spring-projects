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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.model.hr.Employee;
import com.example.Taller1QuinteroLuisa.model.person.Countryregion;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.CountryRegionRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryServiceImp;

@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
public class SalesTerritoryUnitTest {
	private Salesterritory st;
	private Countryregion cr;
	
	@Mock
	private SalesTerritoryRepository territoryRepository;
	@Mock
	private CountryRegionRepository countryRegionRepository;
	
	@InjectMocks
	private SalesTerritoryServiceImp salesTerritoryService;
	
	@BeforeAll
	static void init() {
		System.out.println("--------------- SALESTERRITORY TESTING -----------------");
		System.out.println(" ");
	}
	
	@Nested
	@DisplayName("Save methods for sales territory")
	class saveTerritory {
		@Test
		void saveSalesTerritoryCorrect() throws Exception {
			//Set up
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			
			cr= new Countryregion();
			when(countryRegionRepository.findById("+40")).thenReturn(Optional.of(cr));
			when(territoryRepository.save(st)).thenReturn(st);
			
			//Method
			
			Salesterritory temp = salesTerritoryService.save(st, "+40");
			
			//Asserts
			assertNotNull(temp);
			assertEquals(new BigDecimal(200), temp.getCostlastyear());
			assertEquals(new BigDecimal(30), temp.getCostytd());
			assertEquals(new BigDecimal(400), temp.getSaleslastyear());
			assertEquals(new BigDecimal(40), temp.getSalesytd());
			assertEquals("Sarumi", temp.getName());
			assertEquals("Xiao", temp.getSalesGroup());
			assertEquals(st.getTerritoryid(), temp.getTerritoryid());
			
			verify(countryRegionRepository).findById("+40");
			verify(territoryRepository).save(st);
		}
		
		@Test
		void salesTerritoryNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				salesTerritoryService.save(null, null);
			});
		}
		
		@Test
		void salesTerritoryWrongName() throws ParseException {
			st= new Salesterritory();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setModifieddate(time);
			try {
				st.setName("h");
				
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> st.getName());
				assertEquals("El nombre debe tener al menos 5 caracteres", exception.getMessage());
			}			
		}
		
		@Test
		void salesTerritoryNullName() throws ParseException{
			st= new Salesterritory();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			st.setModifieddate(time);
			st.setName(null);
			
			try {
				assertThrows(NullPointerException.class, () ->{salesTerritoryService.save(st, "+40");});
			}catch(NullPointerException e) {
				assertNull(st.getName());
				verify(territoryRepository, times(0)).save(st);
			}
		}
		
		@Test
		void countryRegionWrong() throws Exception {
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			
			Salesterritory temp = salesTerritoryService.save(st, "+40");
			
			assertNull(temp);	
		}
		
		@Test
		void salesTerritoryCostLastYearNull() throws Exception {
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(null);
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			
			assertNull(st.getCostlastyear());

			verify(territoryRepository, times(0)).save(st);
		}
		
		@Test
		void salesTerritoryCostTyTd() throws ParseException {
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(null);
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			
			assertNull(st.getCostytd());

			verify(territoryRepository, times(0)).save(st);
		}
		
		@Test
		void salesTerritorySalesLastYear() throws ParseException {
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(null);
			st.setSalesytd(new BigDecimal(40));
			
			assertNull(st.getSaleslastyear());

			verify(territoryRepository, times(0)).save(st);
		}
		
		@Test
		void salesTerritorySalesyTd() throws ParseException {
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(null);
			
			assertNull(st.getSalesytd());

			verify(territoryRepository, times(0)).save(st);
		}
		
		@Test
		void salesTerritoryGroupEmpty() throws Exception {
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			
			Salesterritory temp = salesTerritoryService.save(st, "+40");
			
			assertNull(temp);

			verify(territoryRepository, times(0)).save(st);
		}
		
		@Test
		void salesTerritoryGroupNull() throws Exception {
			st= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("14-04-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			
			st.setName("Sarumi");
			st.setModifieddate(time);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup(null);
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			
			Salesterritory temp = salesTerritoryService.save(st, "+40");
			
			assertNull(temp);

			verify(territoryRepository, times(0)).save(st);
		}
		
	}
	
	@AfterEach
	void tearDown() {
		st = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSON TEST ENDED -----------------");
	}
}
