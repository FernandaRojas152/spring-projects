package com.example.Taller1QuinteroLuisa.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.Taller1QuinteroLuisa.model.sales.Salesperson;
import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;

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
	public List<Salesperson> queryPerson(Salesterritory salesterritory, Date start, Date end);
	

}
