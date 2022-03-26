package com.example.Taller1QuinteroLuisa.services;

import java.math.BigDecimal;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.EmployeeRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@Service
public class SalesPersonServiceImp implements SalesPersonService{
	private SalesPersonRepository sp;
	private SalesTerritoryRepository st;
	private EmployeeRepository e;

	@Autowired
	public SalesPersonServiceImp(SalesPersonRepository sp, SalesTerritoryRepository st, EmployeeRepository e) {
		this.e= e;
		this.sp= sp;
		this.st= st;
	}

	@Override
	public Salesperson save(Salesperson s, Integer territory) throws Exception {
		Salesperson temp= null;
		
		validateConstrains(s);

		Optional<Salesterritory> optional = this.st.findById(territory);
		if(optional.isPresent()) {
			s.setSalesterritory(optional.get());
			//s.setSalesterritory(st.getById(s.getSalesterritory().getTerritoryid()));
			temp= this.sp.save(s);
		}
		return temp;
	}

	@Override
	public Salesperson update(Salesperson s, Integer id) throws Exception {
		Salesperson temp= null;
		Optional<Salesperson> optional = sp.findById(s.getBusinessentityid());
		
		if(optional.isPresent()) {
//			Salesperson p= sp.getById(s.getBusinessentityid());
//			p.setModifieddate(s.getModifieddate());
//			p.setSaleslastyear(s.getSaleslastyear());
//			p.setBonus(s.getBonus());
//			p.setCommissionpct(s.getCommissionpct());
//			p.setRowguid(s.getRowguid());
//			p.setSalesquota(s.getSalesquota());
//			p.setSalesytd(s.getSalesytd());
//			p.setSalesterritory(st.getById(s.getSalesterritory().getTerritoryid()));
			validateConstrains(s);
			temp= save(s, id);
		}

		return temp;
	}

	@NotNull
	private void validateConstrains(Salesperson s) throws Exception{
		if(!(s.getCommissionpct().compareTo(BigDecimal.ZERO) >= 0) || !(s.getCommissionpct().compareTo(BigDecimal.ZERO) <= 1)) {
			throw new RuntimeException("El porcentaje de comision no esta entre 0 y 1");
		}
		if(!(s.getSalesquota().signum()!=-1)){
			throw new RuntimeException("La cuota no es mayor que 0");
		}
		
	}
}
