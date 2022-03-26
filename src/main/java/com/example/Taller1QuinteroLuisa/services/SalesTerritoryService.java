package com.example.Taller1QuinteroLuisa.services;

import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;

public interface SalesTerritoryService {
	
	public Salesterritory save(Salesterritory t, String id) throws Exception;
	public Salesterritory update(Salesterritory t, String id) throws Exception;

}
