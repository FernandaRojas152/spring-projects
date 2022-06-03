package com.example.Taller1QuinteroLuisa.backend.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;

@Service
public class SalesPersonServiceImp implements SalesPersonService{
	private SalesPersonRepository sp;
	private SalesTerritoryRepository st;
	
	@Autowired
	public SalesPersonServiceImp(SalesPersonRepository sp, SalesTerritoryRepository st) {
		this.sp= sp;
		this.st= st;
	}

	@Override
	public Salesperson save(Salesperson s) throws Exception {
		Salesperson temp= null;
		
		validateConstrains(s);

		Optional<Salesterritory> optional = this.st.findById(s.getSalesterritory().getTerritoryid());
		if(optional.isPresent()) {
			s.setSalesterritory(optional.get());
			temp= this.sp.save(s);
		}
		return temp;
	}

	@Override
	public Salesperson update(Salesperson s) throws Exception {
		Salesperson temp= null;
		if(s.getBusinessentityid()!=null) {
			Optional<Salesperson> optional = sp.findById(s.getBusinessentityid());
			if(optional.isPresent()) {
				temp= save(s);
			}
		}
		return temp;
	}

	@NotNull
	private void validateConstrains(Salesperson s) throws Exception{
		if(s.getCommissionpct()!=null && !(s.getCommissionpct().compareTo(BigDecimal.ZERO) >= 0) || !(s.getCommissionpct().compareTo(BigDecimal.ZERO) <= 1)) {
			throw new RuntimeException("El porcentaje de comision no esta entre 0 y 1");
		}
		if(s.getSalesquota()!=null && !(s.getSalesquota().signum()!=-1)){
			throw new RuntimeException("La cuota no es mayor que 0");
		}
	}
	
	public Iterable<Salesperson> findAll(){
		return sp.findAll();
	}
	
	public Iterable<Salesterritory> findAllTerritories(){
		return st.findAll();
	}
	
	public Optional<Salesperson> findById(Integer id){
		return sp.findById(id);
	}
	
	public Iterable<Salesperson> findByTerritory(Integer id){
		Salesterritory t= st.findById(id).get();
		List<Salesperson> personList= t.getSalespersons();
		
		Iterable<Salesperson> personI= personList;
		return personI;
	}
}
