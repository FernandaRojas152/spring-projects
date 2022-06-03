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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.backend.model.person.Countryregion;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.repository.CountryRegionRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryServiceImp;

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
		@DisplayName("Save Test Correctly")
		void saveSalesTerritoryCorrect() throws Exception {
			//Set up
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-11-10");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));

			cr= new Countryregion();
			when(countryRegionRepository.findById("+40")).thenReturn(Optional.of(cr));
			when(territoryRepository.save(st)).thenReturn(st);

			//Method
			Salesterritory temp = salesTerritoryService.save(st);

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
		@DisplayName("Save a null territory")
		void salesTerritoryNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				salesTerritoryService.save(null);
			});
		}

		@Test
		@DisplayName("Saving a territory with a wrong name")
		void salesTerritoryWrongName() throws ParseException {
			st= new Salesterritory();
			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setModifieddate(date);
			try {
				st.setName("h");

			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> st.getName());
				assertEquals("El nombre debe tener al menos 5 caracteres", exception.getMessage());
			}			
		}

		@Test
		@DisplayName("Saving a territory with a null name")
		void salesTerritoryNullName() throws ParseException{
			st= new Salesterritory();
			LocalDate date  = LocalDate.parse("2022-04-14");
			st.setModifieddate(date);
			st.setName(null);

			try {
				assertThrows(NullPointerException.class, () ->{salesTerritoryService.save(st);});
			}catch(NullPointerException e) {
				assertNull(st.getName());
				verify(territoryRepository, times(0)).save(st);
			}
		}

		@Test
		@DisplayName("Saving a countryRegion with a wrong ID")
		void countryRegionWrong() throws Exception {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));

			Salesterritory temp = salesTerritoryService.save(st);

			assertNull(temp);	
		}

		@Test
		@DisplayName("Saving cost last year null")
		void salesTerritoryCostLastYearNull() throws Exception {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(null);
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));

			assertNull(st.getCostlastyear());

			verify(territoryRepository, times(0)).save(st);
		}

		@Test
		@DisplayName("Saving costTyTd null")
		void salesTerritoryCostTyTdNull() throws ParseException {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(null);
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));

			assertNull(st.getCostytd());

			verify(territoryRepository, times(0)).save(st);
		}

		@Test
		@DisplayName("Saving sales last year null")
		void salesTerritorySalesLastYearNull() throws ParseException {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(null);
			st.setSalesytd(new BigDecimal(40));

			assertNull(st.getSaleslastyear());

			verify(territoryRepository, times(0)).save(st);
		}

		@Test
		@DisplayName("Saving territory salesyTd null")
		void salesTerritorySalesyTdNull() throws ParseException {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(null);

			assertNull(st.getSalesytd());

			verify(territoryRepository, times(0)).save(st);
		}

		@Test
		@DisplayName("Saving a group empty")
		void salesTerritoryGroupEmpty() throws Exception {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));

			Salesterritory temp = salesTerritoryService.save(st);

			assertNull(temp);

			verify(territoryRepository, times(0)).save(st);
		}

		@Test
		@DisplayName("Saving a group null")
		void salesTerritoryGroupNull() throws Exception {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup(null);
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));

			Salesterritory temp = salesTerritoryService.save(st);

			assertNull(temp);

			verify(territoryRepository, times(0)).save(st);
		}
	}

	@Nested
	@DisplayName("Update methods for sales territory")
	class UpdateSalesTerritory{
		@Test
		@DisplayName("Update Test Correct")
		void updateSalesTerritoryCorrect() throws Exception {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");

			st.setName("Sarumi");
			st.setTerritoryid(57);
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			
			Salesterritory st2= new Salesterritory();
			cr= new Countryregion();
			
			when(countryRegionRepository.findById("+40")).thenReturn(Optional.of(cr));
			when(territoryRepository.findById(57)).thenReturn(Optional.of(st2));
			when(territoryRepository.save(st)).thenReturn(st);

			//Method

			Salesterritory temp = salesTerritoryService.update(st);
			
			assertNotNull(temp);
			assertEquals(new BigDecimal(200), temp.getCostlastyear());
			assertEquals(new BigDecimal(30), temp.getCostytd());
			assertEquals(new BigDecimal(400), temp.getSaleslastyear());
			assertEquals(new BigDecimal(40), temp.getSalesytd());
			assertEquals("Sarumi", temp.getName());
			assertEquals("Xiao", temp.getSalesGroup());
			assertEquals(st.getTerritoryid(), temp.getTerritoryid());
			
			verify(countryRegionRepository).findById("+40");
			verify(territoryRepository, times(1)).save(st);
			verify(territoryRepository).findById(57);
		}
		
		@Test
		@DisplayName("Updating a territory to null")
		void salesTerriroryUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				salesTerritoryService.update(null);
			});
		}
		
		@Test
		@DisplayName("Update territory with an invalid name")
		void updateSalesTerritoryWrongName() throws Exception {
			st= new Salesterritory();

			LocalDate date  = LocalDate.parse("2022-04-14");
			
			st.setTerritoryid(57);
			st.setModifieddate(date);
			st.setCostlastyear(new BigDecimal(200));
			st.setCostytd(new BigDecimal(30));
			st.setSalesGroup("Xiao");
			st.setSaleslastyear(new BigDecimal(400));
			st.setSalesytd(new BigDecimal(40));
			cr= new Countryregion();
			Salesterritory temp= null;
			try {
				st.setName("F");
				temp = salesTerritoryService.update(st);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> st.getName());
				assertEquals("El nombre debe tener al menos 5 caracteres", exception.getMessage());
				assertNull(temp);
			}	
			
			verify(countryRegionRepository, times(0)).findById("+40");
			verify(territoryRepository, times(0)).save(st);
			verify(territoryRepository).findById(57);
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
		System.out.println("--------------- SALESTERRITORY TEST ENDED -----------------");
	}
}
