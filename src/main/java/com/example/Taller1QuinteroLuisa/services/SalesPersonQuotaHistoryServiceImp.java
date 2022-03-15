package com.example.Taller1QuinteroLuisa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Taller1QuinteroLuisa.model.person.Businessentity;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.model.sales.SalespersonquotahistoryPK;
import com.example.Taller1QuinteroLuisa.repository.BusinessentityRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;

@Service
public class SalesPersonQuotaHistoryServiceImp implements SalesPersonQuotaHistoryService{
	private SalesPersonQuotaHistoryRepository spq;
	private BusinessentityRepository be;
	private SalesPersonRepository person;
	
	@Autowired
	public SalesPersonQuotaHistoryServiceImp(SalesPersonQuotaHistoryRepository spq, SalesPersonRepository person, BusinessentityRepository be) {
		this.spq= spq;
		this.person= person;
		this.be= be;
	}
	
	public void save(Salespersonquotahistory s) {
		Salesperson o= s.getSalesperson();
		SalespersonquotahistoryPK pk= new SalespersonquotahistoryPK();
		Integer p= pk.getBusinessentityid();
		if(be.getById(p)!= null) {
			
		}
		//s.setSalesperson(spq.getById(s.getSalesperson()));
		spq.save(s);
	}

}
