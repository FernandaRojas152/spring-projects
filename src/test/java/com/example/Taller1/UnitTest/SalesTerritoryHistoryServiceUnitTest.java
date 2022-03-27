package com.example.Taller1.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.services.SalesTerritoryHistoryServiceImp;

@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
public class SalesTerritoryHistoryServiceUnitTest {
	private Salesterritoryhistory territoryHistory;
	private Salesperson person;
	private Salesterritory territory;

	@Mock
	private SalesTerritoryHistoryRepository territoryHistoryRepo;
	@Mock
	private SalesPersonRepository personRepo;
	@Mock
	private SalesTerritoryRepository territoryRepo;

	@InjectMocks
	private SalesTerritoryHistoryServiceImp territoryService;

	@BeforeAll
	static void init() {
		System.out.println("--------------- SALESTERRITORYHISTORY TESTING -----------------");
		System.out.println(" ");
	}

	@Nested
	@DisplayName("Save methods for sales territory history")
	class saveTerritoryHistory{
		@BeforeEach
		void setUp() throws ParseException {
			territoryHistory= new Salesterritoryhistory();
			person= new Salesperson();
			territory= new Salesterritory();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("06-01-2022");
			Date date2= df.parse("14-04-2022");
			long time1 = date.getTime();
			long time2= date2.getTime();
			Timestamp time = new Timestamp(time1);
			Timestamp timeEnd= new Timestamp(time2);
			
			territoryHistory.setModifieddate(time);
			territoryHistory.setEnddate(timeEnd);
		}
		
		@Test
		@DisplayName("Save a null person quota")
		void salesPersonQuotaNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				territoryService.save(null, null, null);
			});
		}
		
		@Test
		@DisplayName("Save Test Correctly")
		void savePersonQuotaCorrect() throws Exception {
			when(personRepo.findById(152)).thenReturn(Optional.of(person));
			when(territoryRepo.findById(57)).thenReturn(Optional.of(territory));
			when(territoryHistoryRepo.save(territoryHistory)).thenReturn(territoryHistory);

			//Method
			Salesterritoryhistory temp = territoryService.save(territoryHistory, 57, 152);

			//Asserts
			assertNotNull(temp);
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			Date dateTemp  = f.parse("06-01-2022");
			Date dateTemp2= f.parse("14-04-2022");
			
			assertEquals(new Timestamp(dateTemp.getTime()), temp.getModifieddate());
			assertEquals(new Timestamp(dateTemp2.getTime()), temp.getEnddate());
			
			verify(personRepo).findById(152);
			verify(territoryRepo).findById(57);
			verify(territoryHistoryRepo).save(territoryHistory);
			verify(territoryHistoryRepo, times(1)).save(territoryHistory);
		}


	}



	@AfterEach
	void tearDown() {
		person = null;
		territoryHistory= null;
		territory = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println(" ");
		System.out.println("--------------- SALESTERRITORYHISTORY TEST ENDED -----------------");
	}

}
