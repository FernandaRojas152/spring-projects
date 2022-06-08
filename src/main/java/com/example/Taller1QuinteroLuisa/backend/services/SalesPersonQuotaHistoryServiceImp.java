package com.example.Taller1QuinteroLuisa.backend.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Taller1QuinteroLuisa.backend.dao.SalesPersonQuotaHistoryDaoImp;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;

@Service
public class SalesPersonQuotaHistoryServiceImp implements SalesPersonQuotaHistoryService{
	
	@Autowired
	private SalesPersonQuotaHistoryDaoImp personquotaDao;
	
	private SalesPersonQuotaHistoryRepository spq;
	private SalesPersonRepository person;
	
	@Autowired
	public SalesPersonQuotaHistoryServiceImp(SalesPersonQuotaHistoryRepository spq, SalesPersonRepository person) {
		this.spq= spq;
		this.person= person;
	}
	
	@Override
	public Salespersonquotahistory save(Salespersonquotahistory sales) throws Exception{
		//Salespersonquotahistory temp = null;
		validateConstraints(sales);
		
		Optional<Salesperson> optional = this.person.findById(sales.getSalesperson().getBusinessentityid());
		if(optional.isPresent()) {
			sales.setSalesperson(optional.get());
			this.personquotaDao.save(sales);
		}
		
		return sales;
	}

	@Override
	public Salespersonquotahistory update(Salespersonquotahistory sales) throws Exception {
		//Salespersonquotahistory temp = null;
		
		Optional<Salespersonquotahistory> optional = spq.findById(sales.getBusinessentityid());
		if(optional.isPresent()) {
			//validateConstraints(sales);
			personquotaDao.update(sales);
		}
		return sales;
	}
	
	public void delete(Integer id){
		spq.delete(spq.findById(id).get());
	}
	
	@NotNull
	private void validateConstraints(Salespersonquotahistory sales) throws RuntimeException{
		if(!(sales.getSalesquota().compareTo(BigDecimal.ZERO) > 0)){
			throw new RuntimeException("La cuota no es mayor que 0");
		}
	}
	
	public Iterable<Salespersonquotahistory> findAll(){
		return personquotaDao.findAll();
	}
	
	public Iterable<Salesperson> findAllSalesPerson(){
		return person.findAll();
	}
	
	public Optional<Salespersonquotahistory> findById(Integer id){
		return Optional.of(personquotaDao.findById(id));
	}
	
	public Iterable<Salespersonquotahistory> findBySalesPerson(Integer id){
		Salesperson p= person.findById(id).get();
		List<Salespersonquotahistory> quotaList= p.getSalespersonquotahistories();
		Iterable<Salespersonquotahistory> quotaI= quotaList;
		
		return quotaI;
		
	}
	
}
