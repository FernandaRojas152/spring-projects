package com.example.Taller1QuinteroLuisa.services;

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;

public class SalesPersonServiceImp implements SalesPersonService{
	
	private SalesPersonRepository sp;
	public SalesPersonServiceImp(SalesPersonRepository sp) {
		this.sp= sp;
	}
	
	public void save() {
		sp.save(new Salesperson());
	}
	
	public void edit() {
		
	}
}
