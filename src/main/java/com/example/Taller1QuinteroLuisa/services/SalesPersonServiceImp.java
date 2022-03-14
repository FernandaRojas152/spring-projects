package com.example.Taller1QuinteroLuisa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@Service
public class SalesPersonServiceImp implements SalesPersonService{
	
	private SalesPersonRepository sp;
	private SalesTerritoryRepository st;
	
	@Autowired
	public SalesPersonServiceImp(SalesPersonRepository sp, SalesTerritoryRepository st) {
		this.sp= sp;
		this.st= st;
	}
	
	public void save(Salesperson s) {
		s.setSalesterritory(st.getById(s.getSalesterritory().getTerritoryid()));
		sp.save(s);
	}
	
	public void update(Salesperson s) {
		Salesperson p= sp.getById(s.getBusinessentityid());
		p.setModifieddate(s.getModifieddate());
		p.setSaleslastyear(s.getSaleslastyear());
		p.setBonus(s.getBonus());
		p.setCommissionpct(s.getCommissionpct());
		p.setRowguid(s.getRowguid());
		p.setSalesquota(s.getSalesquota());
		p.setSalesytd(s.getSalesytd());
		p.setSalesterritory(st.getById(s.getSalesterritory().getTerritoryid()));
		
		sp.save(p);
	}
}
