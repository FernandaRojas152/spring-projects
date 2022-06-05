package com.example.Taller1QuinteroLuisa.backend.dao;

import java.math.BigDecimal;
import java.util.List;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Salespersonquotahistory;

public interface SalesPersonQuotaHistoryDAO {
	public void save(Salespersonquotahistory salespersonquotahistory);
	public void update(Salespersonquotahistory salespersonquotahistory);
	public List<Salespersonquotahistory> findAll();
	public Salespersonquotahistory findById(Integer id);
	public List<Salespersonquotahistory> findBySalesPerson(Integer salespersonid);
	public List<Salespersonquotahistory> findBySalesQuota(BigDecimal salesquota);

}
