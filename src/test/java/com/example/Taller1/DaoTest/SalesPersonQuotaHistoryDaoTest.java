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
			
			assertTrue(salespersonQuotaDAO.findById(salespersonquotahistory.getId()).equals(salespersonquotahistory));
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void update() {
			assertNotNull(salespersonQuotaDAO);
			salespersonQuotaDAO.save(salespersonquotahistory);

			date= LocalDate.parse("2022-04-16");
			salespersonquotahistory.setModifieddate(date);
			salespersonquotahistory.setSalesquota(new BigDecimal(999));
			
			Salespersonquotahistory temp= salespersonQuotaDAO.findById(salespersonquotahistory.getId());
			assertAll(
					()-> assertEquals(new BigDecimal(999), temp.getSalesquota()));
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAll() {
			assertNotNull(salespersonQuotaDAO);
			salespersonQuotaDAO.save(salespersonquotahistory);
			
			assertEquals(1, salespersonQuotaDAO.findAll().size());
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findBySalesQuota() {
			assertNotNull(salespersonQuotaDAO);
			salespersonQuotaDAO.save(salespersonquotahistory);
			
			Salespersonquotahistory salespersonquotahistory1 = new Salespersonquotahistory();
			LocalDate date1= LocalDate.parse("2022-04-14");
			
			Salesperson salesperson1= new Salesperson();
			salesperson1.setBonus(new BigDecimal(152));
			salesperson1.setCommissionpct(BigDecimal.ZERO);
			salesperson1.setSalesquota(new BigDecimal(700));
			salespersonDAO.save(salesperson1);
			
			salespersonquotahistory1.setSalesperson(salesperson1);
			salespersonquotahistory1.setSalesquota(new BigDecimal(132));
			salespersonquotahistory1.setModifieddate(date1);
			salespersonQuotaDAO.save(salespersonquotahistory1);
			
			List<Salespersonquotahistory> listPersonQuota= salespersonQuotaDAO.findBySalesQuota(salespersonquotahistory1.getSalesquota());
			
			assertEquals(1, listPersonQuota.size());
			assertEquals(new BigDecimal(132), salespersonquotahistory1.getSalesquota());
		}
		
		@Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findBySalesPerson() {
			assertNotNull(salespersonQuotaDAO);
			salespersonQuotaDAO.save(salespersonquotahistory);
			
			Salespersonquotahistory salespersonquotahistory1 = new Salespersonquotahistory();
			LocalDate date1= LocalDate.parse("2022-04-14");
			
			Salesperson salesperson1= new Salesperson();
			salesperson1.setBonus(new BigDecimal(152));
			salesperson1.setCommissionpct(BigDecimal.ZERO);
			salesperson1.setSalesquota(new BigDecimal(700));
			salespersonDAO.save(salesperson1);
			
			salespersonquotahistory1.setSalesperson(salesperson1);
			salespersonquotahistory1.setSalesquota(new BigDecimal(132));
			salespersonquotahistory1.setModifieddate(date1);
			salespersonQuotaDAO.save(salespersonquotahistory1);
			
			List<Salespersonquotahistory> listPerson= salespersonQuotaDAO.findBySalesPerson((salespersonquotahistory1.getSalesperson().getBusinessentityid()));
			
			assertEquals(1, listPerson.size());
			assertEquals(salespersonquotahistory1.getSalesperson().getBusinessentityid(), salesperson1.getBusinessentityid());
		}
		
		
		
		
	}
}
