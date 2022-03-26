package com.example.Taller1.UnitTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.model.person.Businessentity;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.repository.BusinessentityRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonQuotaHistoryServiceImp;

@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@ExtendWith(MockitoExtension.class)
public class SalesPersonQuotaHistoryServiceUnitTest {
	private Salespersonquotahistory personQuota;
	private Salesperson person;
	private Businessentity business;
	
	@Mock
	private SalesPersonQuotaHistoryRepository personQuotaRepo;
	
	@Mock
	private SalesPersonRepository personRepo;
	
	@Mock
	private BusinessentityRepository businessRepo;
	
	@InjectMocks
	private SalesPersonQuotaHistoryServiceImp personQuotaService;
	
	
	@BeforeAll
	static void init() {
		System.out.println("--------------- SALESTERRITORY TESTING -----------------");
		System.out.println(" ");
	}
	
	@Nested
	@DisplayName("Save methods for sales person quota history")
	class savePersonQuota{
		
	}
	
	@AfterAll
	static void end() {
		System.out.println(" ");
		System.out.println("--------------- SALESPERSON TEST ENDED -----------------");
	}

}
