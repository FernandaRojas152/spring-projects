package com.example.Taller1QuinteroLuisa.backend.services;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryHistoryRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;

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

	@Override
	public Salesterritoryhistory save(Salesterritoryhistory territory) throws Exception {
		Salesterritoryhistory temp= null;
		validateConstrains(territory);
		
		Optional<Salesterritory> optional= this.st.findById(territory.getSalesterritory().getTerritoryid());
		
		Optional<Salesperson> optional2= this.sp.findById(territory.getSalesperson().getBusinessentityid());
		if(optional.isPresent() && optional2.isPresent()) {
			territory.setSalesterritory(optional.get());
			territory.setSalesperson(optional2.get());
			
			System.out.println("Aqui esta: " + territory.getSalesperson().getBusinessentityid());
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
	
	public void delete(Integer id) {
		sth.delete(sth.findById(id).get());
	}

	@NotNull
	private void validateConstrains(Salesterritoryhistory territory) throws Exception {
		if(territory.getStartdate().isAfter(territory.getEnddate())) {
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
	
	public Iterable<Salesterritoryhistory> findByTerritory(Integer id){
		Salesterritory t= st.findById(id).get();
		List<Salesterritoryhistory> territoryList= t.getSalesterritoryhistories();
		Iterable<Salesterritoryhistory> territoryI= territoryList;
		
		return territoryI;
	}

}
