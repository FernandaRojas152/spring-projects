package com.example.Taller1QuinteroLuisa.services;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.CountryRegionRepository;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@Service
public class SalesTerritoryServiceImp implements SalesTerritoryService{
	
	private SalesTerritoryRepository st;
	private CountryRegionRepository cr;
	
	@Autowired
	public SalesTerritoryServiceImp(SalesTerritoryRepository st, CountryRegionRepository cr) {
		this.st= st;
		this.cr= cr;
	}
	
	
	@Override
	public void save(Salesterritory t) throws Exception {
		if(cr.findById(t.getCountryregioncode()).isPresent()) {
			validateConstraints(t);
			st.save(t);
		}
	}
	
	@Override
	public void update(Salesterritory t) throws Exception{
		if(cr.findById(t.getCountryregioncode()).isPresent()) {
		Salesterritory salesT= st.getById(t.getTerritoryid());
		salesT.setCostlastyear(t.getCostlastyear());
		salesT.setCostytd(t.getCostytd());
		salesT.setCountryregioncode(t.getCountryregioncode());
		salesT.setSalesGroup(t.getSalesGroup());
		salesT.setModifieddate(t.getModifieddate());
		salesT.setName(t.getName());
		salesT.setRowguid(t.getRowguid());
		salesT.setSaleslastyear(t.getSaleslastyear());
		salesT.setCostytd(t.getCostytd());
		validateConstraints(t);
		st.save(t);
		}
	}
	
	@NotNull
	private void validateConstraints(Salesterritory t) throws Exception{
		if(t.getName().length() < 5) {
			throw new Exception("El nombre debe tener al menos 5 caracteres ");
		}
	}
}
