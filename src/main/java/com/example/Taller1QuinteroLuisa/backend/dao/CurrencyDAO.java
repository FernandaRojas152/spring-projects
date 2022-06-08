package com.example.Taller1QuinteroLuisa.backend.dao;

import java.util.List;
import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;

public interface CurrencyDAO{
	public void save(Currency currency);
	public void update(Currency currency);
	public void delete(Currency currency);
	public List<Currency> findAll();
	public Currency findById(Integer id);
	public void deleteAll();
}
