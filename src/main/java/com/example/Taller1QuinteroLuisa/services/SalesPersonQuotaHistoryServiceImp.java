package com.example.Taller1QuinteroLuisa.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonQuotaHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;

@Service
public class SalesPersonQuotaHistoryServiceImp implements SalesPersonQuotaHistoryService{
	private SalesPersonQuotaHistoryRepository spq;
	//private BusinessentityRepository be;
	private SalesPersonRepository person;
	
	@Autowired
	public SalesPersonQuotaHistoryServiceImp(SalesPersonQuotaHistoryRepository spq, SalesPersonRepository person) {
		this.spq= spq;
		this.person= person;
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
		
		Optional<Salespersonquotahistory> optional = spq.findById(sales.getBusinessentityid());
		if(optional.isPresent()) {
			//validateConstraints(sales);
			temp= save(sales, id);
		
		}
		return temp;
	}
	
	@NotNull
	private void validateConstraints(Salespersonquotahistory sales) throws RuntimeException{
		LocalDateTime now = LocalDateTime.now();
		Timestamp timeNow = Timestamp.valueOf(now);
		if(sales.getModifieddate().after(timeNow)){
			throw new RuntimeException("La fecha de inicio no es menor a la fecha actual");
		}
		if(!(sales.getSalesquota().compareTo(BigDecimal.ZERO) > 0)){
			throw new RuntimeException("La cuota no es mayor que 0");
		}
	}
}
