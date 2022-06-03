package com.example.Taller1.DaoTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.example.Taller1QuinteroLuisa.backend.dao.SalesPersonDaoImp;
import com.example.Taller1QuinteroLuisa.backend.dao.SalesTerritoryDaoImp;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = Taller1QuinteroLuisaApplication.class)
public class SalesTerritoryHistoryDaoTest {
	@Autowired
	private SalesPersonDaoImp salespersonDAO;
	private Salesperson salesperson;
	@Autowired
	private SalesTerritoryDaoImp salesterritoryDAO;
	private Salesterritory salesterritory;
	
	private Salesterritoryhistory salesterritoryhistory;
	private SalesTerritoryDaoImp salesterritoryHistoryDAO;

	private LocalDate date;

	@BeforeAll
	public static void init() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSON TESTING -----------------");
		System.out.println(" ");
	}

	@BeforeEach
	public void setUp() {
		salesperson = new Salesperson();
		salesterritory = new Salesterritory();
		salesterritoryhistory = new Salesterritoryhistory();
		date = LocalDate.parse("2022-04-14");
		LocalDate date2= LocalDate.parse("2022-06-16");

		salesterritory.setName("Colombia");
		salesterritory.setCountryregioncode("COL");
		salesterritory.setCostlastyear(new BigDecimal(30));
		salesterritoryDAO.save(salesterritory);

		// salesperson.setBusinessentityid(1);
		salesperson.setBonus(new BigDecimal(152));
		salesperson.setCommissionpct(BigDecimal.ONE);
		salesperson.setModifieddate(date);
		salesperson.setSalesquota(new BigDecimal(400));
		salesperson.setSalesterritory(salesterritory);
		
		salesterritoryhistory.setModifieddate(date);
		salesterritoryhistory.setEnddate(date2);
		salesterritoryhistory.setSalesperson(salesperson);
		salesterritoryhistory.setSalesterritory(salesterritory);
		
	}

	@Nested
	class SalesTerritoryHistoryTests {
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void save() {
			assertNotNull(salesterritoryDAO);
			salesterritoryDAO.save(salesterritory);
		}
	}

}
