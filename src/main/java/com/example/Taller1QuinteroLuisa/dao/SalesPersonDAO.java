package com.example.Taller1QuinteroLuisa.dao;

import java.util.List;

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;

public interface SalesPersonDAO {
	
	public void save(Salesperson salesperson);
	public void update(Salesperson salesperson);
	public List<Salesperson> findAll();
	public Salesperson findById(Integer id);
	
	//Join
	public List<Salesperson> findBySalesTerritory(Integer territoryId);
	

}
