package com.example.Taller1.IntegrationTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.model.hr.Employee;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.EmployeeRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;
import com.example.Taller1QuinteroLuisa.services.SalesPersonServiceImp;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@SpringBootTest
public class SalesPersonIntegrationTest {
	
	private Salesperson s;
	private Employee e;
	private Salesterritory t;
	
	private SalesPersonServiceImp sp;
	private SalesPersonRepository salesPerson;
	private EmployeeRepository ep;
	private SalesTerritoryRepository st;
	
	public SalesPersonIntegrationTest(EmployeeRepository ep, SalesTerritoryRepository st) {
		// TODO Auto-generated constructor stub
		this.ep= ep;
		this.st= st;
		
		this.sp= new SalesPersonServiceImp(salesPerson, st, ep);
	}
}
