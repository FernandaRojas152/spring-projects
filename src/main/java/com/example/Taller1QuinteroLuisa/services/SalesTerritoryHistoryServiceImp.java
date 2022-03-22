package com.example.Taller1QuinteroLuisa.services;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritoryhistory;
import com.example.Taller1QuinteroLuisa.model.sales.SalesterritoryhistoryPK;
import com.example.Taller1QuinteroLuisa.repository.SalesPersonRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryHistoryRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@Service
public class SalesTerritoryHistoryServiceImp implements SalesTerritoryHistoryService{
	SalesTerritoryHistoryRepository sth;
	SalesPersonRepository sp;
	SalesTerritoryRepository st;

	@Autowired
	public SalesTerritoryHistoryServiceImp(SalesTerritoryHistoryRepository sth,SalesPersonRepository sp, SalesTerritoryRepository st) {
		this.sth= sth;
		this.sp= sp;
		this.st= st;
	}

	@Override
	public void save(Salesterritoryhistory territory) throws Exception {
		SalesterritoryhistoryPK pk= new SalesterritoryhistoryPK();
		Integer p= pk.getBusinessentityid();

		//corregir el sp por be
		if(sp.findById(p).isPresent() &&
				st.findById(territory.getSalesterritory().getTerritoryid()).isPresent()) {

			territory.setSalesterritory(st.getById(territory.getSalesterritory().getTerritoryid()));
			validateConstrains(territory);
			sth.save(territory);
		}
	}

	@Override
	public void update(Salesterritoryhistory territory) throws Exception {
		SalesterritoryhistoryPK pk= new SalesterritoryhistoryPK();
		Integer p= pk.getBusinessentityid();

		if(sp.findById(p).isPresent() &&
				st.findById(territory.getSalesterritory().getTerritoryid()).isPresent()) {
			Salesterritoryhistory t= sth.getById(territory.getId());
			t.setEnddate(territory.getEnddate());
			t.setModifieddate(territory.getModifieddate());
			t.setRowguid(territory.getRowguid());
			t.setSalesterritory(st.getById(territory.getSalesterritory().getTerritoryid()));
			validateConstrains(territory);
			sth.save(t);
		}
	}

	@NotNull
	private void validateConstrains(Salesterritoryhistory territory) throws Exception {
		if(territory.getModifieddate().after(territory.getEnddate())) { //fix start date
			throw new Exception("La fecha de inicio no es mejor a la fecha final");
		}
	}

}
