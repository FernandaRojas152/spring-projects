package com.example.Taller1.DaoTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
import com.example.Taller1QuinteroLuisa.dao.SalesPersonDaoImp;
import com.example.Taller1QuinteroLuisa.dao.SalesTerritoryDaoImp;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
class SalesPersonDaoTest{
	//Attributes
	private SalesPersonDaoImp salespersonDAO;
	private SalesTerritoryDaoImp salesterritoryDAO;
	private SalesPersonRepository personRepo;
	private SalesTerritoryRepository territoryRepo;
	private Salesperson salesperson;
	private Salesterritory salesterritory;
	
	private LocalDate date;
	
	@Autowired
	public SalesPersonDaoTest(SalesPersonDaoImp salespersonDAO, SalesTerritoryDaoImp salesterritoryDAO){
		this.salespersonDAO= salespersonDAO;
		this.salesterritoryDAO= salesterritoryDAO;
	}
	
	@BeforeAll
	public static void init() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSON TESTING -----------------");
		System.out.println(" ");
	}
	
	@BeforeEach
	public void setUp() {
		salesperson= new Salesperson();
		salesterritory= new Salesterritory();
		date= LocalDate.parse("2022-04-14");
		
		salesterritory.setName("Colombia");
		salesterritory.setCountryregioncode("COL");
		salesterritory.setCostlastyear(new BigDecimal(30));
		salesterritoryDAO.save(salesterritory);
		
		//salesperson.setBusinessentityid(1);
		salesperson.setBonus(new BigDecimal(152));
		salesperson.setCommissionpct(BigDecimal.ONE);
		salesperson.setModifieddate(date);
		salesperson.setSalesquota(new BigDecimal(400));
		salesperson.setSalesterritory(salesterritory);
	}
	
	@Nested
	class SalesPersonTests{
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void save() {
			assertNotNull(salespersonDAO);
			salespersonDAO.save(salesperson);
			assertTrue(salespersonDAO.findById(salesperson.getBusinessentityid()).equals(salesperson));
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void update() {
			assertNotNull(salespersonDAO);
			salespersonDAO.save(salesperson);
			
			date= LocalDate.parse("2022-04-16");
			
			salesperson.setSalesquota(new BigDecimal(600));
			salesperson.setBonus(new BigDecimal(30));
			salesperson.setModifieddate(date);
			salesperson.setCommissionpct(new BigDecimal(0.3));
			
			Salesperson temp= salespersonDAO.findById(salesperson.getBusinessentityid());
			assertAll(()-> assertEquals(new BigDecimal(600), temp.getSalesquota()));	
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAll(){
			assertNotNull(salespersonDAO);
			salespersonDAO.save(salesperson);
			
			assertEquals(salespersonDAO.findAll().size(), 1);	
		}
		
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByComision(){
			assertNotNull(salespersonDAO);
			salespersonDAO.save(salesperson);
			
			
			Salesperson salesperson1= new Salesperson();
			Salesterritory salesterritory1= new Salesterritory();
			LocalDate date1= LocalDate.parse("2022-02-22");
			
			salesterritory1.setName("Canada");
			salesterritory1.setCountryregioncode("CAN");
			salesterritory1.setCostlastyear(new BigDecimal(4000));
			salesterritoryDAO.save(salesterritory1);
			
			//salesperson1.setBusinessentityid(2);
			salesperson1.setBonus(new BigDecimal(152));
			salesperson1.setCommissionpct(BigDecimal.ZERO);
			salesperson1.setModifieddate(date1);
			salesperson1.setSalesquota(new BigDecimal(700));
			salesperson1.setSalesterritory(salesterritory1);
			salespersonDAO.save(salesperson1);
			
			List<Salesperson> listPerson= salespersonDAO.findByCommision(salesperson1.getCommissionpct());
			
			assertEquals(1, listPerson.size());
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findBySalesQuota() {
			assertNotNull(salespersonDAO);
			salespersonDAO.save(salesperson);
			
			Salesperson salesperson1= new Salesperson();
			Salesterritory salesterritory1= new Salesterritory();
			LocalDate date1= LocalDate.parse("2022-02-22");
			
			salesterritory1.setName("Canada");
			salesterritory1.setCountryregioncode("CAN");
			salesterritory1.setCostlastyear(new BigDecimal(4000));
			salesterritoryDAO.save(salesterritory1);
			
			//salesperson1.setBusinessentityid(2);
			salesperson1.setBonus(new BigDecimal(152));
			salesperson1.setCommissionpct(BigDecimal.ZERO);
			salesperson1.setModifieddate(date1);
			salesperson1.setSalesquota(new BigDecimal(700));
			salesperson1.setSalesterritory(salesterritory1);
			salespersonDAO.save(salesperson1);
			
			List<Salesperson> listPerson= salespersonDAO.findBySalesQuota(salesperson1.getSalesquota());
			
			assertEquals(1, listPerson.size());
			assertEquals(new BigDecimal(700), salesperson1.getSalesquota());
		}
		
		@Test
		
		void findByTerritory() {
			
		}
	}
}
