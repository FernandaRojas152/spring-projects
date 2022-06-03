package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;

public interface SalesTerritoryService {
	public Salesterritory save(Salesterritory t) throws Exception;
	public Salesterritory update(Salesterritory t) throws Exception;

}
