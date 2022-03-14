package com.example.Taller1QuinteroLuisa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;

@Service
public class SalesPersonServiceImp implements SalesPersonService{
	
	private SalesPersonRepository sp;
	
	@Autowired
	public SalesPersonServiceImp(SalesPersonRepository sp) {
		this.sp= sp;
	}
	
	public void save(Salesperson s) {
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
		
		sp.save(p);
	}
	
	public void delete(Salesperson s) {
		sp.delete(s);
	}
}
