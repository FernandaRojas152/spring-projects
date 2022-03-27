package com.example.Taller1.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonServiceImp;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@SpringBootTest
public class SalesPersonIntegrationTest {
	private Salesperson s;
	//private Employee e;
	private Salesterritory t;
	private SalesPersonServiceImp sp;
	private SalesPersonRepository salesPerson;
	//private EmployeeRepository ep;
	private SalesTerritoryRepository st;

	@Autowired
	public SalesPersonIntegrationTest(SalesPersonRepository salesPerson, SalesTerritoryRepository st, SalesPersonServiceImp sp) {
		this.st= st;
		this.sp= sp;
		this.salesPerson= salesPerson;
	}

	@BeforeAll
	static void init() {
		System.out.println("---------------SALESPERSON TESTED-----------------");
	}

	@Nested
	@DisplayName("Save methods for sales person")
	class SaveSalesPerson{	
		@BeforeEach
		void setUp() throws Exception{
			s = new Salesperson();
			t= new Salesterritory();
			s.setBusinessentityid(2215);
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("10-11-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			s.setModifieddate(time);
			s.setSalesquota(new BigDecimal(152));
			s.setCommissionpct(BigDecimal.ZERO);
			s.setBonus(BigDecimal.ONE);	
			
			st.save(t);
			s.setSalesterritory(t);
		}
		
		@Test
		void saveCorrectly() throws Exception {
			Salesterritory temp= new Salesterritory();
			temp.setTerritoryid(57);
			s.setSalesterritory(temp);
			sp.save(s, 57);
			assertNotNull(temp);
			assertNotNull(sp);
		}
		
		@Test
		void saveQuotaCorrect() throws Exception {
			s.setSalesquota(new BigDecimal(1));
			Salesterritory temp= new Salesterritory();
			temp.setTerritoryid(57);
			s.setSalesterritory(temp);
			sp.save(s, 57);
			assertNotNull(sp);
			assertNotNull(s.getSalesquota());
			assertEquals(new BigDecimal(1), s.getSalesquota());
		}
		
		@Test
		void salesPersonNull() {
			Assertions.assertThrows(NullPointerException.class, () -> {
				sp.save(null, null);
			});
		}
		
		@Test
		void wrongPercentage() {
			try {
				s.setCommissionpct(new BigDecimal(80));
			}catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> s.getSalesquota());
				assertEquals("El porcentaje de comision no esta entre 0 y 1", exception.getMessage());
			}
		}
		
		@Test
		void salesPersonWrongSalesQuota() throws Exception {
			try {
				s.setSalesquota(new BigDecimal(-152));
			} catch (RuntimeException e) {
				Throwable exception = assertThrows(RuntimeException.class, () -> s.getSalesquota());
				assertEquals("La cuota no es mayor que 0", exception.getMessage());
			}
		}
	}
	
	@Nested
	@DisplayName("Methods for update sales person")
	class UpdateSalesPerson{
		@BeforeEach
		void setUp() throws Exception{
			s = new Salesperson();
			t= new Salesterritory();
			s.setBusinessentityid(2215);
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date date  = df.parse("10-11-2022");
			long time1 = date.getTime();
			Timestamp time = new Timestamp(time1);
			s.setModifieddate(time);
			s.setSalesquota(new BigDecimal(152));
			s.setCommissionpct(BigDecimal.ZERO);
			s.setBonus(BigDecimal.ONE);
			
			st.save(t);
			s.setSalesterritory(t);
		}
		
		@Test
		void updateSalesPersonCorrectly() throws Exception {
			Salesperson person2= new Salesperson();
			Salesterritory temp= new Salesterritory();
			temp.setTerritoryid(57);
			person2.setSalesterritory(temp);
			sp.update(s, 57);
			
			assertNotNull(sp);
		}
		
		@Test
		void salesPersonUpdateNull() throws Exception{
			Assertions.assertThrows(NullPointerException.class, () -> {
				sp.update(null, null);
			});
		}
		
	}

	@AfterEach
	void tearDown() {
		s = null;
		System.gc();
	}

	@AfterAll
	static void end() {
		System.out.println("--------------- FINISHED -----------------");
	}
}
