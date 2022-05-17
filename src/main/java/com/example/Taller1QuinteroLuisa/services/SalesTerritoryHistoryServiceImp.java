package com.example.Taller1QuinteroLuisa.services;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@Service
public class SalesTerritoryHistoryServiceImp implements SalesTerritoryHistoryService{
	private SalesTerritoryHistoryRepository sth;
	private SalesPersonRepository sp;
	private SalesTerritoryRepository st;

	@Autowired
	public SalesTerritoryHistoryServiceImp(SalesTerritoryHistoryRepository sth,SalesPersonRepository sp, SalesTerritoryRepository st) {
		this.sth= sth;
		this.sp= sp;
		this.st= st;
	}

	@Transactional
	@Override
	public Salesterritoryhistory save(Salesterritoryhistory territory) throws Exception {
		Salesterritoryhistory temp= null;
		validateConstrains(territory);
		
		Optional<Salesterritory> optional= this.st.findById(territory.getSalesterritory().getTerritoryid());
		Optional<Salesperson> optional2= this.sp.findById(territory.getSalesperson().getBusinessentityid());
		if(optional.isPresent() && optional2.isPresent()) {
			territory.setSalesterritory(optional.get());
			territory.setSalesperson(optional2.get());
			
			temp= this.sth.save(territory);
		}
		return temp;
	}

	@Transactional
	@Override
	public Salesterritoryhistory update(Salesterritoryhistory territory) throws Exception {
		Salesterritoryhistory temp= null;

		if(territory.getId()!=null) {
			Optional<Salesterritoryhistory> optional = sth.findById(territory.getId());
			if(optional.isPresent()) {
				temp = save(territory);
			}	
		}
		return temp;
	}

	@NotNull
	private void validateConstrains(Salesterritoryhistory territory) throws Exception {
		if(territory.getModifieddate().isAfter(territory.getEnddate())) {
			throw new Exception("La fecha de inicio no es menor a la fecha final");
		}
	}
	
	public Iterable<Salesterritoryhistory> findAll(){
		return sth.findAll();
	}
	
	public Optional<Salesterritoryhistory> findById(Integer id){
		return sth.findById(id);
	}
	
	public Iterable<Salesperson> findAllSalesPerson(){
		return sp.findAll();
	}
	
	public Iterable<Salesterritory> findAllSalesTerritory(){
		return st.findAll();
	}

}
