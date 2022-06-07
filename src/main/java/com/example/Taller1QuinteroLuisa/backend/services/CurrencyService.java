package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;

public interface CurrencyService {
	public void saveCurrency(Currency currency);
	public void updateCurrency(Currency currency);
	public void deleteCurrency(Integer id);
	public Currency findById(Integer id);
	public Iterable<Currency> findAll();
}

