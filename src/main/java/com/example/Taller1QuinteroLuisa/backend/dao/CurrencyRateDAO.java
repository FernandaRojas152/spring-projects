package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currencyrate;

public interface CurrencyRateDAO{
	public void save(Currencyrate currencyRate);
	public void update(Currencyrate currencyRate);
	public void delete(Currencyrate currencyRate);
	public List<Currencyrate> findAll();
	public Currencyrate findById(Integer id);
	public void deleteAll();
	
	//Join
	public List<Currencyrate> findByCurrency(String code);
}
