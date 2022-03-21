package com.example.Taller1QuinteroLuisa.services;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	@Override
	public void save(Salespersonquotahistory sales) throws Exception{
		SalespersonquotahistoryPK pk= new SalespersonquotahistoryPK();
		Integer p= pk.getBusinessentityid();
		
		if(be.findById(p).isPresent() && person.findById(sales.getSalesperson().getBusinessentityid()).isPresent()) {
			spq.save(sales);
		}
		//s.setSalesperson(spq.getById(s.getSalesperson()));
	}

	@Override
	public void update(Salespersonquotahistory sales) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@NotNull
	private void validateConstraints(Salespersonquotahistory sales) throws Exception{
		if(!(sales.getSalesquota().compareTo(BigDecimal.ZERO) > 0)){
			throw new Exception("La cuota no es mayor que 0");
		}
	}
}
