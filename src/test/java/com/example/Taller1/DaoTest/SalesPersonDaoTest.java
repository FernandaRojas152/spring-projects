package com.example.Taller1.DaoTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
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
		
		salesperson.setBusinessentityid(1);
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
	}
}
