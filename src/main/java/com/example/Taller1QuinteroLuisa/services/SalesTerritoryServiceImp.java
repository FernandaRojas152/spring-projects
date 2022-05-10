package com.example.Taller1QuinteroLuisa.services;

import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;
import com.example.Taller1QuinteroLuisa.repository.SalesTerritoryRepository;

@Service
public class SalesTerritoryServiceImp implements SalesTerritoryService{
	private SalesTerritoryRepository st;

	@Autowired
	public SalesTerritoryServiceImp(SalesTerritoryRepository st) {
		this.st= st;
	}

	@Override
	@Transactional
	public Salesterritory save(Salesterritory t) throws Exception {
		Salesterritory temp = null;
		validateConstraints(t);

		temp= this.st.save(t);

		return temp;
	}

	@Override
	@Transactional
	public Salesterritory update(Salesterritory t) throws Exception{
		Salesterritory temp = null;

		if(t.getTerritoryid()!=null) {
			Optional<Salesterritory> optional = st.findById(t.getTerritoryid());
			if(optional.isPresent()) {
				//validateConstraints(t);
				temp= save(t);
			}
		}

		return temp;
	}

	@NotNull
	private void validateConstraints(Salesterritory t) throws Exception{
		if(t.getName()!=null && t.getName().length() < 5) {
			throw new RuntimeException("El nombre debe tener al menos 5 caracteres");
		}
	}

	public Iterable<Salesterritory> findAll(){
		return st.findAll();
	}

}
