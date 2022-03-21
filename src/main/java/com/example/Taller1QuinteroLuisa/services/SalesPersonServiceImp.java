package com.example.Taller1QuinteroLuisa.services;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

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
	public void save(Salesperson s) throws Exception {
		s.setSalesterritory(st.getById(s.getSalesterritory().getTerritoryid()));
		validateConstrains(s);
		sp.save(s);
	}
	
	@Override
	public void update(Salesperson s) throws Exception {
		Salesperson p= sp.getById(s.getBusinessentityid());
		p.setModifieddate(s.getModifieddate());
		p.setSaleslastyear(s.getSaleslastyear());
		p.setBonus(s.getBonus());
		p.setCommissionpct(s.getCommissionpct());
		p.setRowguid(s.getRowguid());
		p.setSalesquota(s.getSalesquota());
		p.setSalesytd(s.getSalesytd());
		p.setSalesterritory(st.getById(s.getSalesterritory().getTerritoryid()));
		validateConstrains(s);
		sp.save(p);
	}
	
	@NotNull
	private void validateConstrains(Salesperson s) throws Exception{
		if(!(s.getCommissionpct().compareTo(BigDecimal.ZERO) > 0) || !(s.getCommissionpct().compareTo(BigDecimal.ZERO) < 1)) {
			throw new Exception("El porcentaje de comision no esta entre 0 y 1");
		}
		if(!(s.getSalesquota().compareTo(BigDecimal.ZERO) > 0)){
			throw new Exception("La cuota no es mayor que 0");
		}
	}
}
