package com.example.Taller1QuinteroLuisa.services;

import com.example.Taller1QuinteroLuisa.model.sales.Salesterritoryhistory;

public interface SalesTerritoryHistoryService {
	
	public Salesterritoryhistory save(Salesterritoryhistory territory, Integer id, Integer idPerson) throws Exception;
	public Salesterritoryhistory update(Salesterritoryhistory territory, Integer id, Integer idPerson) throws Exception;

}
