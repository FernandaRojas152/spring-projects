package com.example.Taller1QuinteroLuisa.dao;

import java.util.List;

import com.example.Taller1QuinteroLuisa.model.sales.Salespersonquotahistory;

public interface SalesPersonQuotaHistoryDAO {
	public void save(Salespersonquotahistory salespersonquotahistory);
	public void update(Salespersonquotahistory salespersonquotahistory);
	public List<Salespersonquotahistory> findAll();
	public Salespersonquotahistory findById(Integer id);
	public List<Salespersonquotahistory> findBySalesPerson(Integer salespersonid);

}
