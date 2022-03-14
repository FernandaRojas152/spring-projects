package com.example.Taller1QuinteroLuisa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@Service
public class SalesTerritoryServiceImp implements SalesTerritoryService{
	
	SalesTerritoryRepository st;
	
	@Autowired
	public SalesTerritoryServiceImp(SalesTerritoryRepository st) {
		this.st= st;
	}
	
	public void save(Salesterritory t) {
		st.save(t);
	}
	
	public void update(Salesterritory t) {
		Salesterritory salesT= st.getById(t.getTerritoryid());
		
	}
	
	
	
}
