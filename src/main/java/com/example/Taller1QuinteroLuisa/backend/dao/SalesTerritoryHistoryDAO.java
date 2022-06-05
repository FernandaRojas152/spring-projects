package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritoryhistory;

public interface SalesTerritoryHistoryDAO{
	public void save(Salesterritoryhistory salesterritoryhistory);
	public void update(Salesterritoryhistory salesterritoryhistory);
	public List<Salesterritoryhistory> findAll();
	public Salesterritoryhistory findById(Integer id);
	
	//Joins
	public List<Salesterritoryhistory> findBySalesPerson(Integer salespersonid);
	public List<Salesterritoryhistory> findBySalesTerritory(Integer salesterritoryid);
}
