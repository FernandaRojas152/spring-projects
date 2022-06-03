package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;

public interface SalesTerritoryHistoryService {
	
	public Salesterritoryhistory save(Salesterritoryhistory territory) throws Exception;
	public Salesterritoryhistory update(Salesterritoryhistory territory) throws Exception;

}
