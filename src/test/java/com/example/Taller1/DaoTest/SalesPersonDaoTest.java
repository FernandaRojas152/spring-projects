package com.example.Taller1.DaoTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;
import com.example.Taller1QuinteroLuisa.dao.SalesPersonDaoImp;
import com.example.Taller1QuinteroLuisa.dao.SalesTerritoryDaoImp;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
class SalesPersonDaoTest{
	//Attributes
	private SalesPersonDaoImp salespersonDAO;
	private SalesTerritoryDaoImp salesterritoryDAO;
	private SalesPersonRepository personRepo;
	private SalesTerritoryRepository territoryRepo;
	private Salesperson salesperson;
	private Salesterritory salesterritory;
	
	
	

}
