package com.example.Taller1.DaoTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.dao.SalesTerritoryDaoImp;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
public class SalesTerritoryDaoTest {
	@Autowired
	private SalesTerritoryDaoImp salesterritoryDAO;
	
	private SalesPersonRepository personRepo;
	private Salesterritory salesterritory;
	
	@Autowired
	public SalesTerritoryDaoTest(SalesTerritoryDaoImp salesterritoryDAO, SalesPersonRepository personRepo) {
		this.salesterritoryDAO= salesterritoryDAO;
		this.personRepo= personRepo;
	}
	
	@BeforeAll
	public static void init() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSON TESTING -----------------");
		System.out.println(" ");
	}
	
	@BeforeEach
	public void setUp(){
		salesterritory= new Salesterritory();
		
		salesterritory.setName("Colombia");
		salesterritory.setCountryregioncode("COL");
		salesterritory.setCostlastyear(new BigDecimal(30));
		salesterritoryDAO.save(salesterritory);
	}
	
	@Nested
	class SalesTerritoryTests{
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void save() {
			assertNotNull(salesterritoryDAO);
			salesterritoryDAO.save(salesterritory);
			
			assertTrue(salesterritoryDAO.findById(salesterritory.getTerritoryid()).equals(salesterritory));
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void update() {
			assertNotNull(salesterritoryDAO);
			salesterritoryDAO.save(salesterritory);
			
			salesterritory.setCountryregioncode("CAN");
			salesterritory.setName("Canada");
			salesterritory.setCostlastyear(new BigDecimal(800));
			
			Salesterritory temp= salesterritoryDAO.findById(salesterritory.getTerritoryid());
			assertAll(
					()-> assertEquals(new BigDecimal(800), temp.getCostlastyear()),
					()-> assertEquals("Canada", temp.getName()),
					()-> assertEquals("CAN", temp.getCountryregioncode()));
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAll(){
			salesterritoryDAO.save(salesterritory);
			
			assertEquals(salesterritoryDAO.findAll().size(), 1);
		}
		
		
		/** SPECIAL QUERY TEST*/
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findTwoSalesPersonWithQuota(){
			assertNotNull(salesterritoryDAO);
			Salesperson salesperson1= new Salesperson();
			Salesperson salesperson2= new Salesperson();
			
			
			salesperson1.setSalesquota(new BigDecimal(12000));
			salesperson1.setCommissionpct(new BigDecimal(0.5));
			salesperson2.setSalesquota(new BigDecimal(120000));
			salesperson2.setCommissionpct(new BigDecimal(0.5));
			salesperson1.setSalesterritory(salesterritory);
			salesperson2.setSalesterritory(salesterritory);

			personRepo.save(salesperson1);
			personRepo.save(salesperson2);
			
			salesterritoryDAO.save(salesterritory);
			
			List<Salesterritory> territory= salesterritoryDAO.findTwoSalesPersonWithQuota();
			
			assertEquals(1, territory.size());
			
		}
	
	}
	
	
	@AfterAll
	static void end() {
		System.out.println("--------------- SALESPERSON TEST ENDED -----------------");
	}
}
