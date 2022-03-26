package com.example.Taller1QuinteroLuisa.services;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Taller1QuinteroLuisa.model.person.Countryregion;
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
	public Salesterritory save(Salesterritory t, String id) throws Exception {
		Salesterritory temp = null;
		validateConstraints(t);
		Optional<Countryregion> optional = this.cr.findById(id);
		if(optional.isPresent()){
			t.setCountryregioncode(optional.get().getCountryregioncode());
			temp= this.st.save(t);
		}
		return temp;
	}

	@Override
	public Salesterritory update(Salesterritory t, String id) throws Exception{
		Salesterritory temp = null;
		Optional<Salesterritory> optional = st.findById(t.getTerritoryid());
		if(optional.isPresent()) {
			//		Salesterritory salesT= st.getById(t.getTerritoryid());
			//		salesT.setCostlastyear(t.getCostlastyear());
			//		salesT.setCostytd(t.getCostytd());
			//		salesT.setCountryregioncode(t.getCountryregioncode());
			//		salesT.setSalesGroup(t.getSalesGroup());
			//		salesT.setModifieddate(t.getModifieddate());
			//		salesT.setName(t.getName());
			//		salesT.setRowguid(t.getRowguid());
			//		salesT.setSaleslastyear(t.getSaleslastyear());
			//		salesT.setCostytd(t.getCostytd());
			validateConstraints(t);
			temp= save(t, id);
		}
		return temp;
	}

	@NotNull
	private void validateConstraints(Salesterritory t) throws Exception{
		if(t.getName().length() < 5) {
			throw new RuntimeException("El nombre debe tener al menos 5 caracteres");
		}
	}
}
