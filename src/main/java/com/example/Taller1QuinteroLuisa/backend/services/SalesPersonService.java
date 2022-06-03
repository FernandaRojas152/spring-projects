package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;

public interface SalesPersonService {
	
	public Salesperson save(Salesperson s) throws Exception;
	
	public Salesperson update(Salesperson s) throws Exception;
	

}
