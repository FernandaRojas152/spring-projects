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
import com.example.Taller1QuinteroLuisa.dao.SalesPersonQuotaHistoryDaoImp;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
public class SalesPersonQuotaHistoryDaoTest{
	private SalesPersonQuotaHistoryDaoImp salespersonQuotaDAO;
	private SalesPersonDaoImp salespersonDAO;
	private Salesperson salesperson;
	private Salespersonquotahistory salespersonquotahistory;
	private LocalDate date;
	
	@Autowired
	public SalesPersonQuotaHistoryDaoTest(SalesPersonQuotaHistoryDaoImp salespersonQuotaDAO, SalesPersonDaoImp salespersonDAO) {
		this.salespersonQuotaDAO= salespersonQuotaDAO;
		this.salespersonDAO= salespersonDAO;
	}
	
	@BeforeAll
	public static void init() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSON TESTING -----------------");
		System.out.println(" ");
	}
	
	@BeforeEach
	public void setUp() {
		salespersonquotahistory= new Salespersonquotahistory();
		salesperson= new Salesperson();
		date= LocalDate.parse("2022-04-14");
		
		salesperson.setBonus(new BigDecimal(152));
		salesperson.setCommissionpct(BigDecimal.ONE);
		salesperson.setSalesquota(new BigDecimal(400));
		salespersonDAO.save(salesperson);
		
		salespersonquotahistory.setModifieddate(date);
		salespersonquotahistory.setSalesquota(new BigDecimal(500));
		salespersonquotahistory.setSalesperson(salesperson);	
	}
	
	@Nested
	class PersonQuotaTests{
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void save() {
			assertNotNull(salespersonQuotaDAO);
			salespersonQuotaDAO.save(salespersonquotahistory);
			
			assertTrue(salespersonQuotaDAO.findById(salespersonquotahistory.getBusinessentityid()).equals(salespersonquotahistory));
		}
	}
}
