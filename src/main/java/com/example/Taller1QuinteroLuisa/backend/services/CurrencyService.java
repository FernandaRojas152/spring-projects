package com.example.Taller1QuinteroLuisa.backend.services;

import com.example.Taller1QuinteroLuisa.backend.model.sales.Currency;

public interface CurrencyService {
	public void saveCurrency(Currency currency);
	public void updateCurrency(Currency currency);
	public void deleteCurrency(String id);
	public Currency findById(String id);
	public Iterable<Currency> findAll();
}
