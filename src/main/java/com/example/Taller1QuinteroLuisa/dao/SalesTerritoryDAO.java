package com.example.Taller1QuinteroLuisa.dao;

import java.util.List;

import com.example.Taller1QuinteroLuisa.model.sales.Salesterritory;

public interface SalesTerritoryDAO {
	public void save(Salesterritory salesterritory);
	public void update(Salesterritory salesterritory);
	public List<Salesterritory> findAll();
	public Salesterritory findById(Integer id);
	public List<Salesterritory> findTwoSalesPersonWithQuota();
}
