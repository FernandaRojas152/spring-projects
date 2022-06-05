package com.example.Taller1QuinteroLuisa.backend.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;

public interface SalesPersonDAO {
	public void save(Salesperson salesperson);
	public void update(Salesperson salesperson);
	public List<Salesperson> findAll();
	public Salesperson findById(Integer id);
	
	//Join
	public List<Salesperson> findBySalesTerritory(Integer territoryId);
	public List<Salesperson> findByCommision(BigDecimal commissionpct);
	public List<Salesperson> findBySalesQuota(BigDecimal salesquota);
	
	//Query
	public List<Object[]> queryPerson(Salesterritory salesterritory, Date start, Date end);
	

}
