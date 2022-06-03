package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salesterritory;

public interface SalesTerritoryDAO {
	public void save(Salesterritory salesterritory);
	public void update(Salesterritory salesterritory);
	public List<Salesterritory> findAll();
	public Salesterritory findById(Integer id);
	public List<Salesterritory> findTwoSalesPersonWithQuota();
}
