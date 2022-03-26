package com.example.Taller1QuinteroLuisa.services;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public Salespersonquotahistory save(Salespersonquotahistory sales, Integer id) throws Exception{
		Salespersonquotahistory temp = null;
		validateConstraints(sales);
		Optional<Salesperson> optional = this.person.findById(id);
		if(optional.isPresent()) {
			sales.setSalesperson(optional.get());
			temp= this.spq.save(sales);
		}
		
		return temp;
	}

	@Override
	public Salespersonquotahistory update(Salespersonquotahistory sales, Integer id) throws Exception {
		Salespersonquotahistory temp = null;
		SalespersonquotahistoryPK pk= new SalespersonquotahistoryPK();
		Integer p= pk.getBusinessentityid();
		Optional<Salespersonquotahistory> optional = spq.findById(pk);
		
		if(optional.isPresent() || be.findById(p).isPresent()) {
			
//			Salespersonquotahistory spqh = spq.getById(sales.getId());
//			spqh.setModifieddate(sales.getModifieddate());
//			spqh.setRowguid(sales.getRowguid());
//			spqh.setSalesquota(sales.getSalesquota());
//			sales.setSalesperson(person.getById(p)); //Not sure :v
			validateConstraints(sales);
			temp= save(sales, id);
		
		}
		return temp;
	}
	
	@NotNull
	private void validateConstraints(Salespersonquotahistory sales) throws Exception{
		if(!(sales.getSalesquota().compareTo(BigDecimal.ZERO) > 0)){
			throw new Exception("La cuota no es mayor que 0");
		}
	}
}
