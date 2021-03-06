package com.example.Taller1QuinteroLuisa.backend.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Taller1QuinteroLuisa.backend.dao.SalesPersonDaoImp;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.backend.repository.SalesTerritoryRepository;

@Service
public class SalesPersonServiceImp implements SalesPersonService{
	private SalesPersonRepository sp;
	private SalesTerritoryRepository st;
	
	@Autowired
	private SalesPersonDaoImp salespersonDao;
	
	@Autowired
	public SalesPersonServiceImp(SalesPersonRepository sp, SalesTerritoryRepository st) {
		this.sp= sp;
		this.st= st;
	}

	@Override
	public Salesperson save(Salesperson s) throws Exception {
		//Salesperson temp= null;
		
		validateConstrains(s);

		Optional<Salesterritory> optional = this.st.findById(s.getSalesterritory().getTerritoryid());
		if(optional.isPresent()) {
			s.setSalesterritory(optional.get());
			this.salespersonDao.save(s);
		}
		return s;
	}

	@Override
	public Salesperson update(Salesperson s) throws Exception {
		//Salesperson temp= null;
		if(s.getBusinessentityid()!=null) {
			Optional<Salesperson> optional = sp.findById(s.getBusinessentityid());
			if(optional.isPresent()) {
				//temp= save(s);
				salespersonDao.update(s);
			}
		}
		return s;
	}
	
	public void delete(Integer id){
		sp.delete(sp.findById(id).get());
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
		return salespersonDao.findAll();
	}
	
	public Iterable<Salesterritory> findAllTerritories(){
		return st.findAll();
	}
	
	public Optional<Salesperson> findById(Integer id){
		return Optional.of(salespersonDao.findById(id));
	}
	
	public Iterable<Salesperson> findByTerritory(Integer id){
		Salesterritory t= st.findById(id).get();
		List<Salesperson> personList= t.getSalespersons();
		
		Iterable<Salesperson> personI= personList;
		return personI;
	}
}
