package com.example.Taller1.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.backend.model.person.Countryregion;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.repository.CountryRegionRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.backend.services.SalesTerritoryServiceImp;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@SpringBootTest
public class SalesTerritoryIntegrationTest{
	private Salesterritory t;
	private Countryregion cr;
	private SalesTerritoryRepository territoryRepo;
	private CountryRegionRepository countryRepo;
	private SalesTerritoryServiceImp territoryService;
	
	private LocalDate date;
	
	@Autowired
	public SalesTerritoryIntegrationTest(SalesTerritoryRepository territoryRepo,
			CountryRegionRepository countryRepo, SalesTerritoryServiceImp territoryService) {
		this.territoryRepo = territoryRepo;
		this.countryRepo = countryRepo;
		this.territoryService= territoryService;
	}
	
	@BeforeAll
	static void init() {
		System.out.println("---------------SALESPERSON TESTED-----------------");
	}
	
	@Nested
	@DisplayName("Methods for save territory")
	class saveTerritory{
		@BeforeEach
		void setUp() throws Exception{
			t= new Salesterritory();
			cr= new Countryregion();
			t.setTerritoryid(57);
			date= LocalDate.parse("2022-01-22");
			
			t.setModifieddate(date);
			t.setName("Sarumi");
			t.setCostlastyear(new BigDecimal(200));
			t.setCostytd(new BigDecimal(30));
			t.setSalesGroup("Xiao");
			t.setSaleslastyear(new BigDecimal(400));
			t.setSalesytd(new BigDecimal(40));
			//countryRepo.save(cr);
			t.setCountryregioncode(cr.getCountryregioncode());
		}
		
		@Test
		void saveCorrectly() throws Exception {
			Countryregion temp= new Countryregion();
			temp.setCountryregioncode("Spain");
			t.setCountryregioncode(temp.getCountryregioncode());
			
			territoryService.save(t);
			assertNotNull(temp);
			assertNotNull(territoryService);
		}
		
		@Test
		void salesPersonNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				territoryService.save(null);
			});
		}
		
		@Test
		void wrongName() {
			try {
				t.setName("h");

			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> t.getName());
				assertEquals("El nombre debe tener al menos 5 caracteres", exception.getMessage());
			}
		}
		
		@Test
		void wrongCountryCode() throws Exception{
			Countryregion temp= new Countryregion();
			temp.setCountryregioncode(null);
			t.setCountryregioncode(temp.getCountryregioncode());
			
			Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()-> {territoryService.save(t);}); 
		}	
	}
	
	@Nested
	@DisplayName("Update methods for territory")
	class updateTerritory{
		@BeforeEach
		void setUp() throws Exception{
			t= new Salesterritory();
			cr= new Countryregion();
			t.setTerritoryid(57);
			date= LocalDate.parse("2022-10-11");
			
			t.setModifieddate(date);
			t.setName("Sarumi");
			t.setCostlastyear(new BigDecimal(200));
			t.setCostytd(new BigDecimal(30));
			t.setSalesGroup("Xiao");
			t.setSaleslastyear(new BigDecimal(400));
			t.setSalesytd(new BigDecimal(40));
			//countryRepo.save(cr);
			t.setCountryregioncode(cr.getCountryregioncode());
		}
		
		@Test
		void updateCorrectly() throws Exception {
			Salesterritory t2= new Salesterritory();
			Countryregion temp= new Countryregion();
			temp.setCountryregioncode("Spain");
			t2.setCountryregioncode(temp.getCountryregioncode());
			
			territoryService.save(t);
			assertNotNull(territoryService);
		}
		
		@Test
		void salesPersonUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				territoryService.update(null);
			});
		}
	}
	
	@AfterEach
	void tearDown() {
		t = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println("--------------- FINISHED -----------------");
	}
	
	
	

}
