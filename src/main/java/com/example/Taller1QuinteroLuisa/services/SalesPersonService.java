package com.example.Taller1QuinteroLuisa.services;

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;

public interface SalesPersonService {
	
	public Salesperson save(Salesperson s, Integer id) throws Exception;
	
	public Salesperson update(Salesperson s, Integer id) throws Exception;
	

}
