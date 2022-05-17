package com.example.Taller1.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
	Salesterritoryhistory t2;

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
			LocalDate date  = LocalDate.parse("2022-01-06");
			LocalDate date2  = LocalDate.parse("2022-04-16");

			territoryHistory.setModifieddate(date);
			territoryHistory.setEnddate(date2);
		}

		@Test
		@DisplayName("Save a null person quota")
		void saveTerritoryHistoryNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				territoryService.save(null);
			});
		}

		@Test
		@DisplayName("Save Test Correctly")
		void saveTerritoryHistoryCorrect() throws Exception {
			when(personRepo.findById(152)).thenReturn(Optional.of(person));
			when(territoryRepo.findById(57)).thenReturn(Optional.of(territory));
			when(territoryHistoryRepo.save(territoryHistory)).thenReturn(territoryHistory);

			//Method
			Salesterritoryhistory temp = territoryService.save(territoryHistory);

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

		@Test
		@DisplayName("Saving a date bigger than the end one")
		void wrongInitialDate() throws ParseException {
			LocalDate date  = LocalDate.parse("2022-04-16");
			try {
				territoryHistory.setModifieddate(date);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getModifieddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
		}
		
		@Test
		@DisplayName("Saving a end date smaller than the initial one")
		void wrongEndDate() throws ParseException {
			LocalDate date  = LocalDate.parse("2021-01-01");

			try {
				territoryHistory.setEnddate(date);;
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getEnddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
		}
		
		@Test
		@DisplayName("Saving a end date smaller than the initial one")
		void nullInitialDate() throws ParseException {
			try {
				territoryHistory.setEnddate(null);;
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getEnddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
		}
		
		@Test
		@DisplayName("Saving a end date smaller than the initial one")
		void nullEndDate() throws ParseException {
			try {
				territoryHistory.setEnddate(null);;
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getEnddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
		}
	}
	
	@Nested
	@DisplayName("Update methods for sales territory history")
	class UpdateSalesTerritoryHistory{
		@BeforeEach
		void setUp() throws ParseException {
			territoryHistory= new Salesterritoryhistory();
			person= new Salesperson();
			territory= new Salesterritory();
			
			LocalDate date  = LocalDate.parse("2022-01-06");
			LocalDate date2  = LocalDate.parse("2022-04-14");

			territoryHistory.setId(2215);
			territoryHistory.setModifieddate(date);
			territoryHistory.setEnddate(date2);
			t2= new Salesterritoryhistory();
		}
		
		@Test
		@DisplayName("Save a null territory history")
		void updateTerritoryHistoryNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				territoryService.update(null);
			});
		}

		@Test
		@DisplayName("Save Territory History Correctly")
		void updateTerritoryHistoryCorrect() throws Exception {
			when(personRepo.findById(152)).thenReturn(Optional.of(person));
			when(territoryRepo.findById(57)).thenReturn(Optional.of(territory));
			when(territoryHistoryRepo.findById(2215)).thenReturn(Optional.of(t2));
			when(territoryHistoryRepo.save(territoryHistory)).thenReturn(territoryHistory);

			//Method
			Salesterritoryhistory temp = territoryService.update(territoryHistory);

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
		
		@Test
		@DisplayName("Saving a date bigger than the end one")
		void updateWrongInitialDate() throws Exception {
			LocalDate date  = LocalDate.parse("2022-04-16");

			try {
				territoryHistory.setModifieddate(date);
				Salesterritoryhistory temp = territoryService.update(territoryHistory);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getModifieddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
		}
		
		@Test
		@DisplayName("Saving a end date smaller than the initial one")
		void updateWrongEndDate() throws Exception {
			LocalDate date  = LocalDate.parse("2021-01-11");

			try {
				territoryHistory.setEnddate(date);;
				Salesterritoryhistory temp = territoryService.update(territoryHistory);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getEnddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
		}
		
		@Test
		@DisplayName("Saving a end date smaller than the initial one")
		void updateNullInitialDate() throws Exception {
			try {
				territoryHistory.setEnddate(null);
				Salesterritoryhistory temp = territoryService.update(territoryHistory);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getEnddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
		}
		
		@Test
		@DisplayName("Saving a end date smaller than the initial one")
		void updateNullEndDate() throws Exception {
			try {
				territoryHistory.setEnddate(null);
				Salesterritoryhistory temp = territoryService.update(territoryHistory);
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> territoryHistory.getEnddate());
				assertEquals("La fecha de inicio no es menor a la fecha final", exception.getMessage());
			}
			verify(territoryHistoryRepo, times(0)).save(territoryHistory);
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
